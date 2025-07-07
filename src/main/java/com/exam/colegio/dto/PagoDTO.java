package com.exam.colegio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class PagoDTO {

        private int idPago;
        private BigDecimal pay;
        private String description;

}
