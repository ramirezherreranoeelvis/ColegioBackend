package com.exam.colegio.repository.course.content;

import com.exam.colegio.model.course.content.SessionAttendance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gatomontes
 */
@Repository
public interface ISessionAttendanceRepository extends JpaRepository<SessionAttendance, Integer> {
        Optional<SessionAttendance> findByStudentDniAndSessionIdContent(String dni, Integer idContent);
}
