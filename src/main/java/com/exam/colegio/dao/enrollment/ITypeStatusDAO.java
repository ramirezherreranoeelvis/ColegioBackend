package com.exam.colegio.dao.enrollment;

import com.exam.colegio.model.enrollment.TypeStatus;

import java.util.Optional;
public interface ITypeStatusDAO {

        Optional<TypeStatus> getPendiente();

        Optional<TypeStatus> getCancelado();

        Optional<TypeStatus> getAnulado();

        Optional<TypeStatus> findById(int id);

}
