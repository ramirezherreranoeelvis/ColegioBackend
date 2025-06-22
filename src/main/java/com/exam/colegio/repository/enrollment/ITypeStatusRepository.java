package com.exam.colegio.repository.enrollment;

import com.exam.colegio.model.enrollment.TypeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeStatusRepository extends JpaRepository<TypeStatus, Integer> {

}
