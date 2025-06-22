package com.exam.colegio.model.enrollment;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "season")
public class Season {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idSeason;

        @Column(name = "startDate")
        private Date startDate;

        @Column(name = "year", nullable = false, updatable = false, unique = true)
        private String year;

}
