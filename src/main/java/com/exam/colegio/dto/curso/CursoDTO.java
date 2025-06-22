package com.exam.colegio.dto.curso;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

import com.exam.colegio.model.course.CourseScheduled;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class CursoDTO {

        private String codigo;
        private String nombre;
        private int numeroSalon;
        private int piso;
        private LocalTime horaInicio;
        private LocalTime horaFin;
        private String dia;
        private List<String> profesores;
        private String portada;
        private int numeroSesiones;
        private List<ContentDTO> contenidos;

        public static CursoDTO buildByCourse(CourseScheduled courseScheduled) {
                var classroom = courseScheduled.getClassroom();
                var course = courseScheduled.getCourse();
                var enrollment = courseScheduled.getEnrollment();
                return CursoDTO.builder()
                                .codigo(courseScheduled.getCode())
                                .numeroSalon(classroom.getNumber())
                                .piso(classroom.getFloor())
                                .nombre(course.getName() + "-"
                                                + enrollment.getSeason().getYear())
                                .horaInicio(courseScheduled.getStartTime())
                                .horaFin(courseScheduled.getEndTime())
                                .dia(courseScheduled.getDayOfWeek().getDisplayName())
                                .profesores(courseScheduled.getTeacherCourseScheduleds()
                                                .stream()
                                                .map(teacherCourseScheduled -> {
                                                        var teacher = teacherCourseScheduled
                                                                        .getTeacher();
                                                        return teacher.getName() + " " + teacher
                                                                        .getSurnamePaternal()
                                                                        + " "
                                                                        + teacher.getSurnameMaternal();
                                                }).toList())
                                .portada(courseScheduled.getPortada())
                                .build();
        }

}
