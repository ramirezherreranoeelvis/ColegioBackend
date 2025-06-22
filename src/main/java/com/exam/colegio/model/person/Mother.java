package com.exam.colegio.model.person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("mother")
public class Mother extends Person {

        @OneToMany(mappedBy = "mother", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Student> students = new ArrayList<>();

        @Builder
        public Mother(int idPerson, String dni, String name, String surnamePaternal, String surnameMaternal, String phoneNumber, Access access) {
                super(idPerson, dni, name, surnamePaternal, surnameMaternal, phoneNumber, access);
        }


}