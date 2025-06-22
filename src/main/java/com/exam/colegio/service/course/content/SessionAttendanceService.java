package com.exam.colegio.service.course.content;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.colegio.dao.course.content.ISessionAttendanceDAO;
import com.exam.colegio.model.course.StatusAttendance;
import com.exam.colegio.model.course.content.SessionAttendance;
import com.exam.colegio.model.course.content.SessionContent;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.repository.course.content.ISessionAttendanceRepository;
import com.exam.colegio.util.MessageResponse;

@Service
@Transactional
public class SessionAttendanceService implements ISessionAttendanceDAO {

        @Override
        @Transactional
        public ResponseEntity<?> registerAttendance(Student student, SessionContent session,
                        StatusAttendance statusAttendance) {
                // comprobar que no habra una asistencia ya registrada
                var attendances = this.sessionAttendanceRepository.findByStudentDniAndSessionIdContent(student.getDni(),
                                session.getIdContent());
                if (attendances.isPresent()) {
                        return ResponseEntity.badRequest().body("Ya existe una asistencia registrada.");
                }
                var response = this.sessionAttendanceRepository
                                .save(entity(student, session, statusAttendance));
                if (response == null) {
                        return ResponseEntity.badRequest().body("hubo un error al registrar la asistencia.");
                }
                return ResponseEntity.ok(MessageResponse.message("Se registro su asistencia exitosamente"));
        }

        @Override
        @Transactional
        public ResponseEntity<?> registerExitAttendance(Student student, SessionContent session) {
                // existe asistencia
                var attendancesOptional = this.sessionAttendanceRepository.findByStudentDniAndSessionIdContent(
                                student.getDni(), session.getIdContent());
                if (attendancesOptional.isEmpty()) {
                        return ResponseEntity.badRequest().body("No existe un ingreso para marcar salida.");
                }
                // verificar que no este marcado la salida
                var attendance = attendancesOptional.get();
                if (attendance.getTimeExit() != null) {
                        return ResponseEntity.badRequest().body("La salida ya fue registrada");
                }
                attendance.setTimeExit(new Date());
                if (this.sessionAttendanceRepository.save(attendance) == null) {
                        return ResponseEntity.badRequest().body("hubo un error al registrar la salida.");
                }
                return ResponseEntity.ok(MessageResponse.message("Se registro su salida exitosamente"));
        }

        private SessionAttendance entity(Student student, SessionContent session,
                        StatusAttendance statusAttendance) {
                return SessionAttendance.builder()
                                .student(student)
                                .status(statusAttendance)
                                .session(session)
                                .build();
        }

        @Autowired
        private ISessionAttendanceRepository sessionAttendanceRepository;

}
