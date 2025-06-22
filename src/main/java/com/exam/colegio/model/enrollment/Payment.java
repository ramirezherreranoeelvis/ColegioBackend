package com.exam.colegio.model.enrollment;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idPayment;

        @ManyToOne
        @JoinColumn(name = "idEnrollmentStudent", nullable = false)
        private EnrollmentStudent enrollmentStudent;

        @ManyToOne
        @JoinColumn(name = "idTypeStatus", nullable = false)
        private TypeStatus typeStatus;

        @Column(name = "pay", precision = 6, scale = 2)
        private BigDecimal pay;

        @Column(name = "description", length = 50)
        private String description;

}
