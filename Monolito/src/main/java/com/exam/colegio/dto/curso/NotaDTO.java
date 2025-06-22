package com.exam.colegio.dto.curso;

import lombok.*;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class NotaDTO {
        private String comentario;
        private BigDecimal nota;
        private String fechaCalificacion;
}
