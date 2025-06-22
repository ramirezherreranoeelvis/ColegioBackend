package com.exam.colegio.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.colegio.dao.person.ITeacherDAO;
import com.exam.colegio.dto.curso.CursoDTO;
import com.exam.colegio.repository.course.ICourseScheduledRepository;
import com.exam.colegio.repository.person.ITeacherRepository;

@Service
public class TeacherService implements ITeacherDAO {

        @Override
        public ResponseEntity<?> findCoursesByYear(String year, String dni) {
                var cursos = this.courseScheduledRepository.findByYearAndTeacher(year, dni);
                return ResponseEntity.ok(cursos.stream().map(CursoDTO::buildByCourse).toList());
        }

        @Autowired
        private ITeacherRepository teacherRepository;
        @Autowired
        ICourseScheduledRepository courseScheduledRepository;

}
