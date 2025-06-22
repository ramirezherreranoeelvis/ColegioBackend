package com.exam.colegio.service.person;

import com.exam.colegio.dao.person.IFatherDAO;
import com.exam.colegio.model.person.Father;
import com.exam.colegio.repository.person.IFatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FatherService implements IFatherDAO {

        @Override
        public Optional<Father> findByDni(String dni) {
                return fatherRepository.findByDni(dni);
        }

        @Autowired
        private IFatherRepository fatherRepository;

}
