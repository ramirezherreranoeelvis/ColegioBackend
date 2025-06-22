package com.exam.colegio.repository.report;

import com.exam.colegio.model.report.ReportPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportPersonRepository extends JpaRepository<ReportPerson, Integer> {

}
