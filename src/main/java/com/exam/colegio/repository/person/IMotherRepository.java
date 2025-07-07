package com.exam.colegio.repository.person;

import com.exam.colegio.model.person.Mother;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMotherRepository extends JpaRepository<Mother, Integer> {

        Optional<Mother> findByDni(String dni);

}
