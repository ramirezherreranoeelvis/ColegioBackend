package com.exam.colegio.service.enrollment;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exam.colegio.dao.enrollment.ITypeStatusDAO;
import com.exam.colegio.model.enrollment.TypeStatus;
import com.exam.colegio.repository.enrollment.ITypeStatusRepository;

@Service
public class TypeStatusService implements ITypeStatusDAO {

        @Override
        public Optional<TypeStatus> getPendiente() {
                return this.typeStatusRepository.findAll().stream()
                                .filter(typeStatus -> typeStatus.getName().equals("pendiente")).findFirst();
        }

        @Override
        public Optional<TypeStatus> getCancelado() {
                return this.typeStatusRepository.findAll().stream()
                                .filter(typeStatus -> typeStatus.getName().equals("cancelado")).findFirst();
        }

        @Override
        public Optional<TypeStatus> getAnulado() {
                return this.typeStatusRepository.findAll().stream()
                                .filter(typeStatus -> typeStatus.getName().equals("anulado")).findFirst();
        }

        @Override
        public Optional<TypeStatus> findById(int id) {
                return this.typeStatusRepository.findById(id);
        }

        private final ITypeStatusRepository typeStatusRepository;

        public TypeStatusService(ITypeStatusRepository typeStatusRepository) {
                this.typeStatusRepository = typeStatusRepository;
        }

}
