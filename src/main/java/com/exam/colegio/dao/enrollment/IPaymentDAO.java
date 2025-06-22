package com.exam.colegio.dao.enrollment;

import com.exam.colegio.model.enrollment.Payment;

import java.util.List;
import java.util.Optional;
public interface IPaymentDAO {

        List<Payment> findPaymentByTypeStatusId(int typeStatusId);

        List<Payment> findPaymentByTypeStatusName(String typeStatusName);

        Optional<Payment> findById(int id);

        Payment save(Payment payment);

}
