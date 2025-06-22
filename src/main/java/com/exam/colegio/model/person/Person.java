package com.exam.colegio.model.person;

import com.exam.colegio.annotation.Dni;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(
        name = "typePerson",
        discriminatorType = DiscriminatorType.STRING,
        length = 20
)
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
@Table(name = "person")
public abstract class Person {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idPerson;

        @Dni
        @Column(name = "dni", nullable = false, unique = true, updatable = false, length = 8)
        private String dni;

        @Column(name = "name", nullable = false, length = 250)
        private String name;

        @Column(name = "surnamePaternal", nullable = false, length = 50)
        private String surnamePaternal;

        @Column(name = "surnameMaternal", nullable = false, length = 50)
        private String surnameMaternal;

        @Column(name = "phoneNumber", length = 15)
        private String phoneNumber;

        @ManyToOne
        @JoinColumn(name = "idAccess", nullable = false, unique = true, updatable = false)
        private Access access;

        public String getTypePerson() {
                if (this instanceof Student) {
                        return "student";
                } else if (this instanceof Teacher) {
                        return "teacher";
                } else if (this instanceof Assistant) {
                        return "assistant";
                } else if (this instanceof Director) {
                        return "director";
                } else if (this instanceof Father) {
                        return "father";
                } else if (this instanceof Mother) {
                        return "mother";
                } else if (this instanceof Representative) {
                        return "representative";
                } else {
                        return "unknown";
                }
        }

}
