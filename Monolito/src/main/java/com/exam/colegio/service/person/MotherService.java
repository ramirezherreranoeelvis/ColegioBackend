package com.exam.colegio.service.person;

import com.exam.colegio.dao.person.IMotherDAO;
import com.exam.colegio.model.person.Mother;
import com.exam.colegio.repository.person.IMotherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MotherService implements IMotherDAO {

        @Override
        public Optional<Mother> findByDni(String dni) {
                return motherRepository.findByDni(dni);
        }

        @Autowired
        private IMotherRepository motherRepository;

}
