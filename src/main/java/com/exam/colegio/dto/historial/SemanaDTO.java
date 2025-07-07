package com.exam.colegio.dto.historial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SemanaDTO {

        private String name;
        private List<DiaIngreso> dias;

}
