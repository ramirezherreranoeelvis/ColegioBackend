package com.exam.colegio.model.enrollment;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrollmentPeriod")
public class EnrollmentPeriod {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idEnrollmentPeriod;

        @ManyToOne
        @JoinColumn(name = "idEnrollment", nullable = false)
        private Enrollment enrollment;

        @ManyToOne
        @JoinColumn(name = "idPeriod", nullable = false)
        private Period period;

        @Column(name = "visibilityDate")
        private boolean visibilityDate;

}