package com.exam.colegio.model.enrollment;

import com.exam.colegio.model.person.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrollmentStudent")
public class EnrollmentStudent {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idEnrollmentStudent;

        @ManyToOne
        @JoinColumn(name = "idStudent", nullable = false)
        private Student student;

        @ManyToOne
        @JoinColumn(name = "idEnrollment", nullable = false)
        private Enrollment enrollment;

        @JsonIgnore
        @OneToMany(mappedBy = "enrollmentStudent", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Payment> payments = new ArrayList<>();
}
