package com.exam.colegio.dao.course;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.exam.colegio.model.course.content.Content;

public interface IContentDAO {
        ResponseEntity<?> findStudentsByContent(Integer id);
        Optional<Content> findById(Integer id);
}
