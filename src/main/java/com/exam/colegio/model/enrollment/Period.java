package com.exam.colegio.model.enrollment;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "period")
public class Period {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idPeriod;

        @Column(name = "startDate", nullable = false)
        private Date startDate;

        @Column(name = "endDate", nullable = false)
        private Date endtDate;

        @JsonIgnore
        @OneToMany(mappedBy = "period", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<EnrollmentPeriod> enrollmentPeriods = new ArrayList<>();

}
