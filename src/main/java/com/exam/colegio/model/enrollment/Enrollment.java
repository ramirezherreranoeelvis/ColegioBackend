package com.exam.colegio.model.enrollment;

import com.exam.colegio.model.other.Grade;
import com.exam.colegio.model.course.CourseScheduled;
import com.exam.colegio.util.TypePeriod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enrollment")
public class Enrollment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idEnrollment;

        @ManyToOne
        @JoinColumn(name = "idSeason", nullable = false)
        private Season season;

        @ManyToOne
        @JoinColumn(name = "idGrade", nullable = false)
        private Grade grade;

        @Column(name = "vacancies", nullable = false)
        private int vacancies;

        @Column(name = "enrolled", nullable = false)
        private int enrolled;

        @Column(name = "cost", nullable = false, precision = 6, scale = 2)
        private BigDecimal cost;

        @Column(name = "monthlyFee", nullable = false, precision = 6, scale = 2)
        private BigDecimal monthlyFee;

        @Column(name = "months", nullable = false)
        private int months;

        @Enumerated(EnumType.STRING)
        @Column(name = "periodType", length = 20, nullable = false)
        private TypePeriod typePeriod;

        @JsonIgnore
        @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<EnrollmentStudent> enrollmentStudents = new ArrayList<>();

        @JsonIgnore
        @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<CourseScheduled> courseScheduleds = new ArrayList<>();

        @JsonIgnore
        @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<EnrollmentPeriod> enrollmentPeriods = new ArrayList<>();

}