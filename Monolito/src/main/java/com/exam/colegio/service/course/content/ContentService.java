package com.exam.colegio.service.course.content;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.colegio.dao.course.IContentDAO;
import com.exam.colegio.dto.StudentCourseDTO;
import com.exam.colegio.model.course.content.Content;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.repository.course.content.IContentRepository;
import com.exam.colegio.repository.person.IStudentRepository;

@Service
public class ContentService implements IContentDAO {

        @Override
        @Transactional(readOnly = true)
        public ResponseEntity<?> findStudentsByContent(Integer id) {
                var students = this.studentRepository.findStudentsByContent(id)
                                .stream()
                                .map(this::convertStudentCourseDTO).toList();
                return ResponseEntity.ok(students);
        }

        @Override
        public Optional<Content> findById(Integer id) {
                return this.contentRepository.findById(id);
        }

        private StudentCourseDTO convertStudentCourseDTO(Student student) {
                return StudentCourseDTO.builder()
                                .dni(student.getDni())
                                .name(student.getName())
                                .surnamePaternal(student.getSurnamePaternal())
                                .surnameMaternal(student.getSurnameMaternal())
                                .phoneNumber(student.getPhoneNumber())
                                .grade(student.getGrade().getName())
                                .build();
        }

        @Autowired
        private IContentRepository contentRepository;
        @Autowired
        private IStudentRepository studentRepository;

}
