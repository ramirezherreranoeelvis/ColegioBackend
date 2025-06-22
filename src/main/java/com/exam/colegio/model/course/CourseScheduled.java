package com.exam.colegio.model.course;

import com.exam.colegio.model.course.content.Content;
import com.exam.colegio.model.course.content.SessionContent;
import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.model.other.TeacherCourseScheduled;
import com.exam.colegio.util.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courseScheduled")
public class CourseScheduled {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idCourseScheduled;

        @Column(name = "code", length = 14, nullable = false, unique = true)
        private String code;

        @ManyToOne
        @JoinColumn(name = "idClassroom", nullable = false)
        private Classroom classroom;

        @ManyToOne
        @JoinColumn(name = "idCourse", nullable = false)
        private Course course;

        @ManyToOne
        @JoinColumn(name = "idEnrollment", nullable = false)
        private Enrollment enrollment;

        @Column(name = "startTime", nullable = false)
        private LocalTime startTime;

        @Column(name = "endTime", nullable = false)
        private LocalTime endTime;

        @Enumerated(EnumType.STRING)
        @Column(name = "day", length = 10, nullable = false)
        private DayOfWeek dayOfWeek;

        @Column(name = "portada", columnDefinition = "LONGTEXT")
        private String portada;

        @JsonIgnore
        @OneToMany(mappedBy = "courseScheduled", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<TeacherCourseScheduled> teacherCourseScheduleds = new ArrayList<>();

        @JsonIgnore
        @OneToMany(mappedBy = "courseScheduled", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<Content> contentList = new ArrayList<>();

        @JsonIgnore
        @OneToMany(mappedBy = "courseScheduled", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<SessionContent> sessionContentList = new ArrayList<>();
}
