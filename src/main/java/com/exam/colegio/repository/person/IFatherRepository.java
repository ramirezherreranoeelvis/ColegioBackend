package com.exam.colegio.repository.person;

import com.exam.colegio.model.person.Father;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFatherRepository extends JpaRepository<Father, Integer> {

        Optional<Father> findByDni(String dni);

}
