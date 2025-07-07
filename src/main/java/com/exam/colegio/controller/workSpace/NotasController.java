package com.exam.colegio.controller.workspace;

import com.exam.colegio.dao.course.ICourseScheduledDAO;
import com.exam.colegio.dao.enrollment.IEnrollmentDAO;
import com.exam.colegio.dao.enrollment.ISeasonDAO;
import com.exam.colegio.dao.person.IStudentDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/notas")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*"
)
@RestController
public class NotasController {

        @GetMapping("/promedios")
        public ResponseEntity<?> obtenerPromediosGenerales(@RequestParam String dniStudent, @RequestParam String year) {
                var studentOptional = this.studentDAO.findByDni(dniStudent);
                var student = studentOptional.get();
                logger.info(student.getIdPerson() + "");
                var seasionOptional = this.seasonDAO.findByYear(year);
                var season = seasionOptional.get();
                logger.info(student.getName());
                var enrollmentOptional = this.enrollmentDAO.findBySeasonAndByStudent(season, student);
                var enrollment = enrollmentOptional.get();
                var promedios = this.courseScheduledDAO.calcularPromedios(enrollment, student);

                return ResponseEntity.ok(promedios);
        }

        private final IStudentDAO studentDAO;
        private final IEnrollmentDAO enrollmentDAO;
        private final ISeasonDAO seasonDAO;
        private final ICourseScheduledDAO courseScheduledDAO;

        public NotasController(IStudentDAO studentDAO, IEnrollmentDAO enrollmentDAO, ISeasonDAO seasonDAO, ICourseScheduledDAO courseScheduledDAO) {
                this.studentDAO = studentDAO;
                this.enrollmentDAO = enrollmentDAO;
                this.seasonDAO = seasonDAO;
                this.courseScheduledDAO = courseScheduledDAO;
        }

        private java.util.logging.Logger logger = java.util.logging.Logger.getLogger(getClass().getName());

}
