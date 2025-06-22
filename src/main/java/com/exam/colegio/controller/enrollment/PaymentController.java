package com.exam.colegio.controller.enrollment;

import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.colegio.dao.enrollment.IPaymentDAO;
import com.exam.colegio.dao.enrollment.ITypeStatusDAO;
import com.exam.colegio.dao.person.IStudentDAO;
import com.exam.colegio.dto.PagoDTO;
import com.exam.colegio.model.enrollment.Payment;
import com.exam.colegio.model.enrollment.TypeStatus;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PaymentController {

        @PostMapping("/processPayment")
        public ResponseEntity<?> processPayment(@RequestParam int idPayment) {
                logger.info(idPayment + "");
                Optional<Payment> optionalPayment = this.paymentDAO.findById(idPayment);

                if (optionalPayment.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pago no encontrado.");
                }

                Payment payment = optionalPayment.get();

                Optional<TypeStatus> canceledStatusOptional = typeStatusDAO.getCancelado();
                if (canceledStatusOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de estado no encontrado");
                }
                TypeStatus canceledStatus = canceledStatusOptional.get();
                payment.setTypeStatus(canceledStatus);

                paymentDAO.save(payment);

                return ResponseEntity.ok("Pago cancelado exitosamente.");
        }

        @GetMapping("/pagosPendientes")
        public ResponseEntity<?> pagosPendientes(@RequestParam String dniStudent) {
                if (dniStudent == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DNI NULL");
                }

                if (!isEightDigitDni.test(dniStudent)) {
                        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("DNI INVALID");
                }

                var studentOptional = this.studentDAO.findByDni(dniStudent);
                if (studentOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found Student");
                }
                var paymentList = this.studentDAO.findPendingPaymentsForStudent(studentOptional.get());
                var paymentListDTO = paymentList.stream().map(payment -> new PagoDTO(payment.getIdPayment(),
                                payment.getPay(), payment.getDescription())).toList();
                return ResponseEntity.ok(paymentListDTO);
        }

        private java.util.logging.Logger logger = java.util.logging.Logger.getLogger(getClass().getName());
        private final Predicate<String> isEightDigitDni = dni -> Optional.ofNullable(dni).filter(d -> d.length() == 8)
                        .isPresent();
        private final IStudentDAO studentDAO;
        private final IPaymentDAO paymentDAO;
        private final ITypeStatusDAO typeStatusDAO;

        public PaymentController(IStudentDAO studentDAO, IPaymentDAO paymentDAO, ITypeStatusDAO typeStatusDAO) {
                this.studentDAO = studentDAO;
                this.paymentDAO = paymentDAO;
                this.typeStatusDAO = typeStatusDAO;
        }

}
