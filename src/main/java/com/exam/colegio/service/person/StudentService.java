package com.exam.colegio.service.person;

import com.exam.colegio.dao.person.IStudentDAO;
import com.exam.colegio.dto.curso.CursoDTO;
import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.model.enrollment.EnrollmentStudent;
import com.exam.colegio.model.enrollment.Payment;
import com.exam.colegio.model.enrollment.Season;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.repository.course.ICourseScheduledRepository;
import com.exam.colegio.repository.person.IStudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Gatomontes
 */
@Service
public class StudentService implements IStudentDAO {

        @Override
        @Transactional(readOnly = true)
        public List<CursoDTO> findCourseSchedulesByTemporada(Student student, Season season) {
                return this.courseScheduledRepository.obtenerPorStudentYPorTemporada(student.getDni(), season.getYear())
                                .stream()
                                .map(CursoDTO::buildByCourse).toList();
        }

        @Override
        @Transactional(readOnly = true)
        public Optional<Student> findByUsername(String username) {
                return studentRepository.findByAccessUsername(username);
        }

        @Override
        @Transactional(readOnly = true)
        public Optional<Student> findByDni(String dni) {
                return studentRepository.findByDni(dni);
        }

        @Override
        @Transactional(readOnly = true)
        public List<Payment> findPendingPaymentsForStudent(Student student) {
                return student.getEnrollmentStudents().stream()
                                .flatMap(enrollmentStudent -> enrollmentStudent.getPayments().stream())
                                .filter(payment -> payment.getTypeStatus().getIdTypeStatus() == 2) // Tipo "pendiente"
                                .collect(Collectors.toList());
        }

        @Override
        @Transactional(readOnly = true)
        public List<Season> findAllSeasonByStudent(Student student) {
                return student.getEnrollmentStudents().stream()
                                .map(EnrollmentStudent::getEnrollment)
                                .map(Enrollment::getSeason)
                                .toList();
        }

        private final IStudentRepository studentRepository;
        private final ICourseScheduledRepository courseScheduledRepository;

        public StudentService(IStudentRepository studentRepository,
                        ICourseScheduledRepository courseScheduledRepository) {
                this.studentRepository = studentRepository;
                this.courseScheduledRepository = courseScheduledRepository;
        }

}
