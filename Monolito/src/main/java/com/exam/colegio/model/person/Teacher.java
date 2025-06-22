package com.exam.colegio.model.person;

import jakarta.persistence.*;
import lombok.*;
import com.exam.colegio.model.other.TeacherCourseScheduled;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("teacher")
public class Teacher extends Person {

        @Builder
        public Teacher(int idPerson, String dni, String name, String surnamePaternal, String surnameMaternal, String phoneNumber, Access access) {
                super(idPerson, dni, name, surnamePaternal, surnameMaternal, phoneNumber, access);
        }

        @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<TeacherCourseScheduled> teacherCourseScheduled = new ArrayList<>();

}
