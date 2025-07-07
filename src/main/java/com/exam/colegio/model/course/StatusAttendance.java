package com.exam.colegio.model.course;

import com.exam.colegio.model.course.content.SessionAttendance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statusAttendance")
public class StatusAttendance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idStatus;

        @Column(name = "name", nullable = false, length = 20)
        private String name;

        @JsonIgnore
        @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<SessionAttendance> sessionAttendances = new ArrayList<>();


}
