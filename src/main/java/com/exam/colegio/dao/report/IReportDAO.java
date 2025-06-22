package com.exam.colegio.dao.report;

import com.exam.colegio.dto.ReporteDTO;

import java.util.List;
public interface IReportDAO {

        List<ReporteDTO> findAllByStudent(String dni);

}
