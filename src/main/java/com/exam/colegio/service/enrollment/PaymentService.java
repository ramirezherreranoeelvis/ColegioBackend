package com.exam.colegio.service.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exam.colegio.dao.enrollment.IPaymentDAO;
import com.exam.colegio.model.enrollment.Payment;
import com.exam.colegio.repository.enrollment.IPaymentRepository;

@Service
public class PaymentService implements IPaymentDAO {

        @Override
        public List<Payment> findPaymentByTypeStatusId(int typeStatusId) {
                return paymentRepository.findByTypeStatusIdTypeStatus(typeStatusId);
        }

        @Override
        public List<Payment> findPaymentByTypeStatusName(String typeStatusName) {
                return paymentRepository.findByTypeStatusName(typeStatusName);
        }

        @Override
        public Optional<Payment> findById(int id) {
                return this.paymentRepository.findById(id);
        }

        @Override
        public Payment save(Payment payment) {
                return this.paymentRepository.save(payment);
        }

        private final IPaymentRepository paymentRepository;

        public PaymentService(IPaymentRepository paymentRepository) {
                this.paymentRepository = paymentRepository;
        }

}
