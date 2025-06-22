package com.exam.colegio.model.report;

import com.exam.colegio.model.person.Person;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reportPerson")
public class ReportPerson {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idReportPerson;

        @ManyToOne
        @JoinColumn(name = "idPerson", nullable = false)
        private Person person;

        @ManyToOne
        @JoinColumn(name = "idReport", nullable = false)
        private Report report;

}
