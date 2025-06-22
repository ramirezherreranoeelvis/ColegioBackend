package com.exam.colegio.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemporadaDTO {

        private Date startDate;
        private String year;

}
