package com.exam.colegio.dto.notas;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Promedios {
        private List<String> periodos;
        private List<Promedio> promedioList;
}
