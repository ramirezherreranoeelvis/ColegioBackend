package com.exam.colegio.dto.curso;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ContentDTO {

        private String nombre;
        private int numero;
        private String tipo;
        private List<ResourceDTO> recursos;

}
