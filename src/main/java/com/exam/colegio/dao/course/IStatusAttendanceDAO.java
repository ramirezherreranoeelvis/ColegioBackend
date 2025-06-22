package com.exam.colegio.dao.course;

import java.util.Optional;

import com.exam.colegio.model.course.StatusAttendance;

public interface IStatusAttendanceDAO {
        Optional<StatusAttendance> findByID(Integer id);
}
