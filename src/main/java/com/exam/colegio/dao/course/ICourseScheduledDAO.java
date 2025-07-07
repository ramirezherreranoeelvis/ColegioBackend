package com.exam.colegio.dao.course;

import com.exam.colegio.dto.curso.CursoDTO;
import com.exam.colegio.dto.notas.Promedios;
import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.model.person.Student;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
public interface ICourseScheduledDAO {

        Optional<CursoDTO> findByCode(String code);

        Optional<CursoDTO> findByCodeByStudent(String code, Student student);

        Promedios calcularPromedios(Enrollment enrollment, Student student);

        ResponseEntity<?> findStudents(CursoDTO courseScheduled);

}
