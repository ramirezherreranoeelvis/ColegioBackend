package com.exam.colegio.repository.course;

import com.exam.colegio.model.course.StatusAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusAttendanceRepository extends JpaRepository<StatusAttendance, Integer> {
}
