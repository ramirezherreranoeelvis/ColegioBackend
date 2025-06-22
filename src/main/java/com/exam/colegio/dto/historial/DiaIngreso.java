package com.exam.colegio.dto.historial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DiaIngreso {

        private String name;
        private EventDTO entrada;
        private EventDTO salida;
        private boolean elementoFinal;

}
