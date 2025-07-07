package com.exam.colegio.model.other;

import com.exam.colegio.model.course.CourseScheduled;
import com.exam.colegio.model.person.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teacherCourseScheduled")
public class TeacherCourseScheduled {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idTeacherCourseScheduled;

        @ManyToOne
        @JoinColumn(name = "idTeacher", nullable = false)
        private Teacher teacher;

        @ManyToOne
        @JoinColumn(name = "idCourseScheduled", nullable = false)
        private CourseScheduled courseScheduled;

        @Column(name = "salary", nullable = false, precision = 6, scale = 2)
        private BigDecimal salary;

}
