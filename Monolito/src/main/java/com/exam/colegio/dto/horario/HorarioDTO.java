package com.exam.colegio.dto.horario;

import java.time.LocalTime;
import java.util.List;

import lombok.*;

/**
 * @author Gatomontes
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class HorarioDTO {

        private List<Day> weekHorario;

        @Getter
        @Setter
        @Builder
        public static class Day {

                private String day;
                private List<Event> cursos;

        }

        @Getter
        @Setter
        @Builder
        public static class Event {

                private String event;
                private LocalTime startTime;
                private LocalTime endTime;

        }


}
