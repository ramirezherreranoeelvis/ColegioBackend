package com.exam.colegio.model.person;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("representative")
public class Representative extends Person {

        @Builder
        public Representative(int idPerson, String dni, String name, String surnamePaternal, String surnameMaternal, String phoneNumber, Access access) {
                super(idPerson, dni, name, surnamePaternal, surnameMaternal, phoneNumber, access);
        }

}
