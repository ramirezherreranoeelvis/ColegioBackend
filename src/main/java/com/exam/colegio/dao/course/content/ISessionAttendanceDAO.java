package com.exam.colegio.dao.course.content;

import org.springframework.http.ResponseEntity;

import com.exam.colegio.model.course.StatusAttendance;
import com.exam.colegio.model.course.content.SessionContent;
import com.exam.colegio.model.person.Student;

public interface ISessionAttendanceDAO {

        ResponseEntity<?> registerAttendance(Student student, SessionContent session,
                        StatusAttendance statusAttendance);

        ResponseEntity<?> registerExitAttendance(Student student, SessionContent session);

}
