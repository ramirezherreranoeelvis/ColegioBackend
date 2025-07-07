package com.exam.colegio.model.course.content.resource;

import com.exam.colegio.model.course.content.resource.activity.Activity;
import com.exam.colegio.model.person.Person;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "gradeActivity")
public class GradeActivity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idGradeActivity;

        @Column(name = "grade", precision = 4, scale = 2, nullable = false)
        private BigDecimal gradeValue;

        @Column(name = "comments", length = 500)
        private String comments;

        @Column(name = "gradedAt")
        @Temporal(TemporalType.TIMESTAMP)
        private Date gradedAt;

        @ManyToOne
        @JoinColumn(name = "idActivity")
        private Activity activity;

        @ManyToOne
        @JoinColumn(name = "idPerson", nullable = false)
        private Person person;

        @PrePersist
        protected void onCreate() {
                gradedAt = new Date();
        }

}
