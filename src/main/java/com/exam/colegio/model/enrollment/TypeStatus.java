package com.exam.colegio.model.enrollment;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "typeStatus")
public class TypeStatus {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idTypeStatus;

        @Column(name = "name", nullable = false, length = 20, unique = true)
        private String name;

}
