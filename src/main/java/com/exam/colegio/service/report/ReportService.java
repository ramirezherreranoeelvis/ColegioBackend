package com.exam.colegio.service.report;

import com.exam.colegio.dao.report.IReportDAO;
import com.exam.colegio.dto.ReporteDTO;
import com.exam.colegio.repository.report.IReportRepository;
import com.exam.colegio.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService implements IReportDAO {

        @Override
        public List<ReporteDTO> findAllByStudent(String dni) {
                var reportes = this.reportRepository.findAllByStudent(dni);
                //transformamos al DTO
                return reportes.stream()
                        .map(report -> {
                                var assistant = report.getAssistant();
                                return ReporteDTO.builder()
                                        .date(DateFormatUtil.ymd(report.getDateReport()))
                                        .description(report.getDescription())
                                        .assistant(assistant.getName() + " " + assistant.getSurnamePaternal() + " " + assistant.getSurnameMaternal())
                                        .phoneNumberAssistant(assistant.getPhoneNumber())
                                        .build();
                        }).toList();
        }

        private final IReportRepository reportRepository;

        @Autowired
        public ReportService(IReportRepository reportRepository) {
                this.reportRepository = reportRepository;
        }

}
