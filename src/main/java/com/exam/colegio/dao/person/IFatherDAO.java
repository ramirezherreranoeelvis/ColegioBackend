package com.exam.colegio.dao.person;

import com.exam.colegio.model.person.Father;

import java.util.Optional;

public interface IFatherDAO {

        Optional<Father> findByDni(String dni);
}
