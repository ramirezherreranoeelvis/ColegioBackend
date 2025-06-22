package com.exam.colegio.repository.enrollment;

import com.exam.colegio.model.enrollment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

        List<Payment> findByTypeStatusName(String typeStatusName);

        List<Payment> findByTypeStatusIdTypeStatus(Integer typeStatusId);

}
