package com.exam.colegio.dao.person;

import java.util.List;
import java.util.Optional;

import com.exam.colegio.dto.curso.CursoDTO;
import com.exam.colegio.model.enrollment.Payment;
import com.exam.colegio.model.enrollment.Season;
import com.exam.colegio.model.person.Student;

public interface IStudentDAO {
        
        List<CursoDTO> findCourseSchedulesByTemporada(Student student, Season season);

        Optional<Student> findByUsername(String username);

        Optional<Student> findByDni(String dni);

        List<Payment> findPendingPaymentsForStudent(Student student);

        List<Season> findAllSeasonByStudent(Student student);

}
