package com.exam.colegio.dao.other;

import com.exam.colegio.dto.historial.SemanaDTO;
import com.exam.colegio.model.person.Student;

import java.util.List;
public interface IEntrySchoolDAO {

        List<SemanaDTO> finAllSemanaDTOByStudent(Student student);

}
