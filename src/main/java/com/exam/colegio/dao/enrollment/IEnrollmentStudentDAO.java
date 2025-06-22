package com.exam.colegio.dao.enrollment;

import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.model.enrollment.EnrollmentStudent;
import com.exam.colegio.model.person.Student;

import java.util.Optional;
public interface IEnrollmentStudentDAO {

        EnrollmentStudent save(EnrollmentStudent enrollmentStudent);

        Optional<EnrollmentStudent> findByStudentAndEnrollment(Student student, Enrollment enrollment);

        boolean isStudentEnrolled(Student student, Enrollment enrollment);

        EnrollmentStudent update(EnrollmentStudent enrollmentStudent);

}
