package com.exam.colegio.service.enrollment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.exam.colegio.dao.enrollment.IEnrollmentDAO;
import com.exam.colegio.dto.horario.HorarioDTO;
import com.exam.colegio.model.course.CourseScheduled;
import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.model.enrollment.EnrollmentStudent;
import com.exam.colegio.model.enrollment.Season;
import com.exam.colegio.model.other.Grade;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.repository.enrollment.IEnrollmentRepository;
import com.exam.colegio.util.DayOfWeek;

@Service
public class EnrollmentService implements IEnrollmentDAO {

        @Override
        public Optional<Enrollment> findById(int idEnrollment) {
                return enrollmentRepository.findById(idEnrollment);
        }

        @Override
        public Optional<Enrollment> addRegisterStudent(Student student, Enrollment enrollment) {
                var enrollmentStudents = enrollment.getEnrollmentStudents();

                //buscamos si ya existe un registro a la matricula ya existente para no guardar por segunda vez
                var enrollmentStudentOptional = enrollmentStudents.stream()
                        .filter(enrollmentStudent -> enrollmentStudent.getStudent().getDni().equals(student.getDni()) &&
                                                     enrollmentStudent.getEnrollment().getIdEnrollment() == enrollment.getIdEnrollment()
                        ).findFirst();
                if (enrollmentStudentOptional.isPresent()) {
                        return Optional.empty();
                }

                // Crear un nuevo EnrollmentStudent y añadirlo a la lista
                enrollmentStudents.add(EnrollmentStudent.builder()
                        .enrollment(enrollment)
                        .student(student)
                        .build());

                // Guardar el NotasController, lo que también debería guardar el nuevo EnrollmentStudent debido al cascade = CascadeType.ALL
                return Optional.of(enrollmentRepository.save(enrollment));
        }

        @Override
        public HorarioDTO getScheduleByEnrollment(Enrollment enrollment) {
                // Obtener todos los cursos programados para esa inscripción y agruparlos por día
                Map<DayOfWeek, List<CourseScheduled>> coursesByDay = enrollment.getCourseScheduleds().stream()
                        .collect(Collectors.groupingBy(CourseScheduled::getDayOfWeek));

                return new HorarioDTO(
                        Arrays.stream(DayOfWeek.values())
                                .map(dayOfWeek -> {
                                        List<HorarioDTO.Event> eventos = new ArrayList<>();
                                        List<CourseScheduled> courses = coursesByDay.getOrDefault(dayOfWeek, Collections.emptyList());

                                        // Ordenar los cursos por hora de inicio
                                        courses.sort(Comparator.comparing(CourseScheduled::getStartTime));

                                        LocalTime lastEndTime = null;

                                        for (CourseScheduled course : courses) {
                                                if (lastEndTime != null && !course.getStartTime().equals(lastEndTime)) {
                                                        // Crear un evento de recreo
                                                        eventos.add(HorarioDTO.Event.builder()
                                                                .event("Recreo")
                                                                .startTime(lastEndTime)
                                                                .endTime(course.getStartTime())
                                                                .build());
                                                }
                                                // Agregar el curso al horario
                                                eventos.add(HorarioDTO.Event.builder()
                                                        .event(course.getCourse().getName())
                                                        .startTime(course.getStartTime())
                                                        .endTime(course.getEndTime())
                                                        .build());

                                                lastEndTime = course.getEndTime();
                                        }

                                        return HorarioDTO.Day.builder()
                                                .day(dayOfWeek.getDisplayName())
                                                .cursos(eventos)
                                                .build();
                                })
                                .collect(Collectors.toList())
                );
        }

        @Override
        public Optional<Enrollment> findByGrade(Grade grade) {
                var idGrade = grade.getIdGrade();
                return this.enrollmentRepository.findAll().stream()
                        .filter(enrollment ->
                                enrollment.getSeason().getYear().equals(String.valueOf(LocalDate.now().getYear() + 1)) &&
                                enrollment.getGrade().getIdGrade() == idGrade
                        )
                        .findFirst();
        }

        @Override
        public Enrollment update(Enrollment enrollment) {
                return this.enrollmentRepository.save(enrollment);
        }

        @Override
        public Optional<Enrollment> findBySeasonAndByStudent(Season season, Student student) {
                return this.enrollmentRepository.findBySeasonAndStudent(season.getIdSeason(), student.getIdPerson());
        }

        private final IEnrollmentRepository enrollmentRepository;

        public EnrollmentService(IEnrollmentRepository enrollmentRepository) {
                this.enrollmentRepository = enrollmentRepository;
        }


}
