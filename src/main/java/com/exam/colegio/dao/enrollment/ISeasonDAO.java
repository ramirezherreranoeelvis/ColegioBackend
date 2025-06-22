package com.exam.colegio.dao.enrollment;

import com.exam.colegio.dto.TemporadaDTO;
import com.exam.colegio.model.enrollment.Season;
import com.exam.colegio.model.person.Student;

import java.util.List;
import java.util.Optional;
public interface ISeasonDAO {

        Optional<Season> findByYear(String year);

        List<TemporadaDTO> findByStudent(Student student);

}
