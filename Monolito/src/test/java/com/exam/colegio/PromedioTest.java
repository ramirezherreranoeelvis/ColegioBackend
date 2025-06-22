package com.exam.colegio;

import com.exam.colegio.dao.person.IStudentDAO;
import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.repository.enrollment.IEnrollmentRepository;
import com.exam.colegio.service.course.CourseScheduledService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PromedioTest {

        @Transactional
        @Test
        void obternetPromedioByIdEnrollment() {
                Enrollment enrollment = this.enrollmentRepository.findById(7).get();
                var promedios = this.courseScheduledService.calcularPromedios(enrollment, this.studentDAO.findByDni("21787088").get());

        }

        private final IEnrollmentRepository enrollmentRepository;

        private final CourseScheduledService courseScheduledService;

        private final IStudentDAO studentDAO;

        @Autowired
        public PromedioTest(IEnrollmentRepository enrollmentRepository, CourseScheduledService courseScheduledService, IStudentDAO studentDAO) {
                this.enrollmentRepository = enrollmentRepository;
                this.courseScheduledService = courseScheduledService;
                this.studentDAO = studentDAO;
        }

}
