package com.exam.colegio.repository.report;

import com.exam.colegio.model.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReportRepository extends JpaRepository<Report, Integer> {

        @Query(value = "CALL usp_getReportsByStudent(:dni)", nativeQuery = true)
        List<Report> findAllByStudent(@Param("dni") String dni);
}
