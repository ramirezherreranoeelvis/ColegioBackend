package com.exam.colegio.dao.person;

import com.exam.colegio.dto.PersonDTO;

import java.util.Optional;

public interface IPersonDAO {

        Optional<PersonDTO> findByUsername(String username, String password);

        Optional<String> getTypeParent(String dni);

}
