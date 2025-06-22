package com.exam.colegio.dto.curso;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ItemDTO {

        private String dniPerson;
        private String tipo;
        private String contenido;
        private String nombreArchivo;

}
