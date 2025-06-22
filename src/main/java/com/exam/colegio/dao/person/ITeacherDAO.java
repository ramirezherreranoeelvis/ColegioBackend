package com.exam.colegio.dao.person;

import org.springframework.http.ResponseEntity;

public interface ITeacherDAO {
        ResponseEntity<?> findCoursesByYear(String year, String dni);
}
