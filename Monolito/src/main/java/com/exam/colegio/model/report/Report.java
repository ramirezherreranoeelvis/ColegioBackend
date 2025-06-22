package com.exam.colegio.model.report;

import com.exam.colegio.model.person.Assistant;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report")
public class Report {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idReport;

        @ManyToOne
        @JoinColumn(name = "idAssistant", nullable = false)
        private Assistant assistant;

        @Column(name = "description", nullable = false, length = 300)
        private String description;

        @Column(name = "dateReport", nullable = false, updatable = false)
        @Temporal(TemporalType.TIMESTAMP)
        private Date dateReport;

        @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ReportPerson> reportPersons = new ArrayList<>();

        @PrePersist
        protected void onCreate() {
                dateReport = new Date();
        }


}
