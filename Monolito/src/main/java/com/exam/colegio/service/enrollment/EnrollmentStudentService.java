package com.exam.colegio.service.enrollment;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exam.colegio.dao.enrollment.IEnrollmentStudentDAO;
import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.model.enrollment.EnrollmentStudent;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.repository.enrollment.IEnrollmentStudentRepository;

@Service
public class EnrollmentStudentService implements IEnrollmentStudentDAO {

        @Override
        public EnrollmentStudent save(EnrollmentStudent enrollmentStudent) {
                return enrollmentStudentRepository.save(enrollmentStudent);
        }

        @Override
        public Optional<EnrollmentStudent> findByStudentAndEnrollment(Student student, Enrollment enrollment) {
                return this.enrollmentStudentRepository.findByStudentAndEnrollment(student.getIdPerson(), enrollment.getIdEnrollment());
        }

        //encontre si ya existe el estudiante registrado a esa matrícula
        @Override
        public boolean isStudentEnrolled(Student student, Enrollment enrollment) {
                return this.enrollmentStudentRepository.findAll().stream()
                        .anyMatch(es -> es.getStudent().getIdPerson() == student.getIdPerson() &&
                                        es.getEnrollment().getIdEnrollment() == enrollment.getIdEnrollment());
        }

        @Override
        public EnrollmentStudent update(EnrollmentStudent enrollmentStudent) {
                return enrollmentStudentRepository.save(enrollmentStudent);
        }

        private final IEnrollmentStudentRepository enrollmentStudentRepository;

        public EnrollmentStudentService(IEnrollmentStudentRepository enrollmentStudentRepository) {
                this.enrollmentStudentRepository = enrollmentStudentRepository;
        }

}
