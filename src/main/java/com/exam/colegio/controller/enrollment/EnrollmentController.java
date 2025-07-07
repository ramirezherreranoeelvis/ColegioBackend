package com.exam.colegio.controller.enrollment;

import com.exam.colegio.dao.enrollment.IEnrollmentDAO;
import com.exam.colegio.dao.enrollment.IEnrollmentStudentDAO;
import com.exam.colegio.dao.enrollment.ITypeStatusDAO;
import com.exam.colegio.dao.person.IStudentDAO;
import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.model.enrollment.EnrollmentStudent;
import com.exam.colegio.model.enrollment.Payment;
import com.exam.colegio.model.person.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.BiPredicate;

/**
 * <h4>Requisitos para registrar matrícula</h4>
 * <ul>
 * <li>Estar registrado en el sistema</li>
 * <li>Estar actualmente trasladado aquí</li>
 * </ul>
 */
@RestController
@RequestMapping("/enrollment")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class EnrollmentController {

        @GetMapping("/horario")
        public ResponseEntity<?> getHorario(@RequestParam int idEnrollment) {
                System.out.println();
                var enrollmentOptional = this.enrollmentDAO.findById(idEnrollment);
                if (enrollmentOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body("No existe una matricula actualmente para este grado escolar");
                }
                var horarioDTO = this.enrollmentDAO.getScheduleByEnrollment(enrollmentOptional.get());
                return ResponseEntity.ok(horarioDTO.getWeekHorario());
        }

        @PostMapping("/registrar")
        public ResponseEntity<?> registerStudentEnrollment(@RequestParam String dniStudent,
                        @RequestParam int idEnrollment) {
                var studentOptional = this.studentDAO.findByDni(dniStudent);
                var enrollmentOptional = this.enrollmentDAO.findById(idEnrollment);
                // validation de alumno y matrícula existente
                if (studentOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
                }
                if (enrollmentOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NotasController Not Found");
                }
                var student = studentOptional.get();
                var enrollment = enrollmentOptional.get();

                // validation de grado y alumno
                BiPredicate<Student, Enrollment> validationGrade = (s,
                                e) -> s.getGrade().getNextGrade().getIdGrade() == e.getGrade().getIdGrade();

                if (!validationGrade.test(student, enrollment)) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body("Esa matricula no es para ese estudiante");
                }

                // validar que no este registrado:
                var enrollmentStudentExist = enrollmentStudentDAO.isStudentEnrolled(student, enrollment);
                if (enrollmentStudentExist) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body("El alumno ya esta registradoa  esta amtricula");
                }

                // Obtener los tipos de pago para ponerlos como falta:
                var pendienteOptional = this.typeStatusDAO.getPendiente();
                if (pendienteOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body("Tipo de estado pendiente no encontrado");
                }
                var pendiente = pendienteOptional.get();

                // creación de lista de pagos
                var payments = new ArrayList<Payment>();

                // creamos el pago de matrícula:
                payments.add(Payment.builder()
                                .typeStatus(pendiente)
                                .pay(enrollment.getCost())
                                .description("Matricula")
                                .build());
                // creamos mensualidades:
                for (int i = 0; i < enrollment.getMonths(); i++) {
                        payments.add(Payment
                                        .builder()
                                        .typeStatus(pendiente)
                                        .pay(enrollment.getMonthlyFee())
                                        .description("mensualidad")
                                        .build());
                }

                // crear enrollmentStudent:
                var enrollmentStudent = this.enrollmentStudentDAO.save(EnrollmentStudent.builder()
                                .student(student)
                                .enrollment(enrollment)
                                .build());

                // resultado:
                var message = new HashMap<String, String>();
                if (enrollmentStudent == null) {
                        message.put("message", "Hubo un error al guardar");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                }

                // a los pagos ahora les agregaos el enrollmentStudent sabiendo que ya se
                // guardo;
                payments.forEach(payment -> payment.setEnrollmentStudent(enrollmentStudent));
                enrollmentStudent.setPayments(payments);
                this.enrollmentStudentDAO.update(enrollmentStudent);

                // actualizamos los matriculados
                enrollment.setEnrolled(enrollment.getEnrolled() + 1);
                this.enrollmentDAO.update(enrollment);

                message.put("message", "Alumno registrado correctamente");
                return ResponseEntity.ok(message);
        }

        private final IStudentDAO studentDAO;
        private final IEnrollmentDAO enrollmentDAO;
        private final IEnrollmentStudentDAO enrollmentStudentDAO;
        private final ITypeStatusDAO typeStatusDAO;

        public EnrollmentController(IStudentDAO studentDAO, IEnrollmentDAO enrollmentDAO,
                        IEnrollmentStudentDAO enrollmentStudentDAO, ITypeStatusDAO typeStatusService) {
                this.studentDAO = studentDAO;
                this.enrollmentDAO = enrollmentDAO;
                this.enrollmentStudentDAO = enrollmentStudentDAO;
                this.typeStatusDAO = typeStatusService;
        }

        // private java.util.logging.Logger logger = java.util.logging.Logger.getLogger(getClass().getName());

}