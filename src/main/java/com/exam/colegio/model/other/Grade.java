package com.exam.colegio.model.other;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grade")
public class Grade {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idGrade;

        @Column(name = "name", nullable = false, unique = true, length = 30)
        private String name;

        @ManyToOne
        @JoinColumn(name = "idNextGrade")
        private Grade nextGrade;

        @ManyToOne
        @JoinColumn(name = "idPreviousGrade")
        private Grade previousGrade;

}
