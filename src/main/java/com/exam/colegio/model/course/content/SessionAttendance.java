package com.exam.colegio.model.course.content;

import com.exam.colegio.model.course.StatusAttendance;
import com.exam.colegio.model.person.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sessionAttendance")
public class SessionAttendance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idAttendance;

        @ManyToOne
        @JoinColumn(name = "idPerson", nullable = false)
        private Student student;

        @ManyToOne
        @JoinColumn(name = "idStatus", nullable = false)
        private StatusAttendance status;

        @ManyToOne
        @JoinColumn(name = "idSession", nullable = false)
        private SessionContent session;

        @Column(name = "timeEntry", nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        private Date timeEntry;

        @Column(name = "timeExit")
        @Temporal(TemporalType.TIMESTAMP)
        private Date timeExit;

        @PrePersist
        protected void onCreate() {
                timeEntry = new Date();
        }

}
