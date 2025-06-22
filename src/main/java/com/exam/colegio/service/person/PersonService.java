package com.exam.colegio.service.person;

import com.exam.colegio.dao.person.IPersonDAO;
import com.exam.colegio.dto.PersonDTO;
import com.exam.colegio.repository.person.IPersonRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonDAO {

        @Autowired
        private IPersonRepository personRepository;

        @Override
        public Optional<PersonDTO> findByUsername(String username, String password) {

                var personOptional = personRepository.findByAccessUsernameAndAccessPassword(username, password);

                return personOptional.map(person -> PersonDTO.builder()
                        .dni(person.getDni())
                        .name(person.getName())
                        .surnamePaternal(person.getSurnamePaternal())
                        .surnameMaternal(person.getSurnameMaternal())
                        .phoneNumber(person.getPhoneNumber())
                        .typePerson(person.getTypePerson())
                        .accessEnabled(person.getAccess().isAccessEnabled())
                        .username(person.getAccess().getUsername())
                        .password(person.getAccess().getPassword())
                        .description(person.getAccess().getDescription())
                        .build()
                );
        }

        @Override
        public Optional<String> getTypeParent(String dni) {
                return personRepository.findAll().stream()
                        .filter(person -> person.getDni().equals(dni))
                        .map(person -> {
                                String typePerson = person.getTypePerson();
                                return typePerson.equals("father") || typePerson.equals("mother") ? typePerson : null;
                        })
                        .findFirst();
        }

}
