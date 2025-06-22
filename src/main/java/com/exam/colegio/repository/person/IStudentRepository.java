package com.exam.colegio.repository.person;

import com.exam.colegio.model.person.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Gatomontes
 */
@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer> {

        Optional<Student> findByAccessUsername(String username);
        Optional<Student> findByDni(String dni);

        @Query(value = "CALL usp_findStudentsByContent(:idContent)", nativeQuery = true)
        List<Student> findStudentsByContent(@Param("idContent") Integer idContent);

        @Query(value = "CALL usp_findStudentsByCourse(:code)", nativeQuery = true)
        List<Student> findStudentsByCourse(@Param("code") String code);

}
