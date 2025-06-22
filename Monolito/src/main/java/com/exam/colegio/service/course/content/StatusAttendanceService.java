package com.exam.colegio.service.course.content;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.colegio.dao.course.IStatusAttendanceDAO;
import com.exam.colegio.model.course.StatusAttendance;
import com.exam.colegio.repository.course.IStatusAttendanceRepository;

@Service
public class StatusAttendanceService implements IStatusAttendanceDAO {

        @Transactional(readOnly = true)
        @Override
        public Optional<StatusAttendance> findByID(Integer id) {
                return this.statusAttendanceRepository.findById(id);
        }

        @Autowired
        private IStatusAttendanceRepository statusAttendanceRepository;
}
