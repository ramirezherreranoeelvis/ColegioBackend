package com.exam.colegio.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReporteDTO {

        private String date;
        private String description;
        private String assistant;
        private String phoneNumberAssistant;

}
