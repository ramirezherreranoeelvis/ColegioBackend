package com.exam.colegio.repository.person;

import com.exam.colegio.model.person.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccessRepository extends JpaRepository<Access, Integer> {

}
