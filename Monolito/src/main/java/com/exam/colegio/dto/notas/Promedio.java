package com.exam.colegio.dto.notas;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Promedio {

        private String nombre;
        private String codigo;
        private List<String> periodos;
        private List<Integer> promedios;

}
