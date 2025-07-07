package com.exam.colegio.dto;

import com.exam.colegio.model.person.Student;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentCourseDTO {
        private String dni;
        private String name;
        private String surnamePaternal;
        private String surnameMaternal;
        private String phoneNumber;
        private String grade;

        public static StudentCourseDTO buildByStudent(Student student) {
                return StudentCourseDTO.builder()
                                .dni(student.getDni())
                                .name(student.getName())
                                .surnamePaternal(student.getSurnamePaternal())
                                .surnameMaternal(student.getSurnameMaternal())
                                .phoneNumber(student.getPhoneNumber())
                                .grade(student.getGrade().getName())
                                .build();
        }

}
