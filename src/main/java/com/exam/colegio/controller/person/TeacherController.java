package com.exam.colegio.controller.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.exam.colegio.dao.course.IContentDAO;
import com.exam.colegio.dao.course.ICourseScheduledDAO;
import com.exam.colegio.dao.course.IStatusAttendanceDAO;
import com.exam.colegio.dao.course.content.ISessionAttendanceDAO;
import com.exam.colegio.dao.enrollment.ISeasonDAO;
import com.exam.colegio.dao.person.IStudentDAO;
import com.exam.colegio.dao.person.ITeacherDAO;
import com.exam.colegio.model.course.content.SessionContent;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TeacherController {

        @GetMapping("/session-students")
        public ResponseEntity<?> findStudentsByContent(@RequestParam Integer idContent) {
                return this.contentDAO.findStudentsByContent(idContent);
        }

        @GetMapping("/course-students")
        public ResponseEntity<?> findStudentsByCourse(@RequestParam String codeCourse) {
                var courseScheduled = this.courseScheduledDAO.findByCode(codeCourse);
                return this.courseScheduledDAO.findStudents(courseScheduled.get());
        }

        @PostMapping("/registerAttendance")
        public ResponseEntity<?> registerAttendance(@Valid @RequestBody AttendanceRequest attendanceRequest) {
                var student = this.studentDAO.findByDni(attendanceRequest.getDniStudent());
                var content = this.contentDAO.findById(attendanceRequest.getIdContent());
                var status = this.statusAttendanceDAO.findByID(attendanceRequest.getIdStatus());
                return this.sessionAttendanceDAO.registerAttendance(student.get(), (SessionContent) content.get(),
                                status.get());
        }

        @PutMapping("/registerExit")
        public ResponseEntity<?> registerExit(@Valid @RequestBody ExitRequest exitRequest) {
                var student = this.studentDAO.findByDni(exitRequest.getDniStudent());
                var content = this.contentDAO.findById(exitRequest.getIdContent());
                return this.sessionAttendanceDAO.registerExitAttendance(student.get(), (SessionContent) content.get());
        }

        @GetMapping("/courses")
        public ResponseEntity<?> findCoursesByYear(@RequestParam String year, @RequestParam String dni) {
                var seasonOptional = this.seasonDAO.findByYear(year);
                if (seasonOptional.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body("No se encontro una la temporada seleccionada");
                }
                var courses = this.teacherDAO.findCoursesByYear(year, dni);
                return courses;
        }

        private final IStatusAttendanceDAO statusAttendanceDAO;
        private final IStudentDAO studentDAO;
        private final ISessionAttendanceDAO sessionAttendanceDAO;
        private final IContentDAO contentDAO;
        private final ICourseScheduledDAO courseScheduledDAO;
        private final ISeasonDAO seasonDAO;
        private final ITeacherDAO teacherDAO;

        public TeacherController(IStatusAttendanceDAO statusAttendanceDAO, IStudentDAO studentDAO,
                        ISessionAttendanceDAO sessionAttendanceDAO, IContentDAO contentDAO,
                        ICourseScheduledDAO courseScheduledDAO, ISeasonDAO seasonDAO, ITeacherDAO teacherDAO) {
                this.statusAttendanceDAO = statusAttendanceDAO;
                this.studentDAO = studentDAO;
                this.sessionAttendanceDAO = sessionAttendanceDAO;
                this.contentDAO = contentDAO;
                this.courseScheduledDAO = courseScheduledDAO;
                this.seasonDAO = seasonDAO;
                this.teacherDAO = teacherDAO;
        }

}

@Data
class AttendanceRequest {
        @NotNull(message = "El DNI no puede ser nulo.")
        @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres.")
        private String dniStudent;
        @NotNull(message = "Debes tener una Sesión donde registrar Asistencia.")
        private Integer idContent;
        @NotNull(message = "Debes tener un estado de asistencia.")
        private Integer idStatus;

}

@Data
class ExitRequest {
        @NotNull(message = "El DNI no puede ser nulo.")
        @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres.")
        private String dniStudent;
        @NotNull(message = "Debes tener una Sesión donde registrar Asistencia.")
        private Integer idContent;
}