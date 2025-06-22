package com.exam.colegio.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
public class MatriculaRegistrarDTO {

        private int idEnrollment;
        private String startDate;
        private String nameGrade;
        private int vacancies;
        private int enrolled;
        private BigDecimal cost;
        private BigDecimal monthlyFee;

}
