package com.exam.colegio.repository.person;

import com.exam.colegio.model.person.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gatomontes
 */
@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {

}
