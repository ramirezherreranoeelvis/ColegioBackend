package com.exam.colegio.service.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.colegio.dao.course.ICourseScheduledDAO;
import com.exam.colegio.dto.StudentCourseDTO;
import com.exam.colegio.dto.curso.ContentDTO;
import com.exam.colegio.dto.curso.CursoDTO;
import com.exam.colegio.dto.curso.ItemDTO;
import com.exam.colegio.dto.curso.ResourceDTO;
import com.exam.colegio.dto.notas.Promedio;
import com.exam.colegio.dto.notas.Promedios;
import com.exam.colegio.model.course.CourseScheduled;
import com.exam.colegio.model.course.content.Content;
import com.exam.colegio.model.course.content.resource.activity.Forum;
import com.exam.colegio.model.course.content.resource.activity.Homework;
import com.exam.colegio.model.course.content.resource.activity.exam.DailyExam;
import com.exam.colegio.model.course.content.resource.activity.exam.MonthlyExam;
import com.exam.colegio.model.course.content.resource.activity.exam.WeeklyExam;
import com.exam.colegio.model.course.content.resource.activity.exam.examfinal.ExamFinal;
import com.exam.colegio.model.enrollment.Enrollment;
import com.exam.colegio.model.enrollment.EnrollmentPeriod;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.repository.course.ICourseScheduledRepository;
import com.exam.colegio.repository.person.IStudentRepository;
import com.exam.colegio.service.course.content.resource.GradeActivityService;
import com.exam.colegio.service.course.content.resource.activity.ActivityService;

@Service
public class CourseScheduledService implements ICourseScheduledDAO {

        @Override
        @Transactional(readOnly = true)
        public Optional<CursoDTO> findByCode(String code) {
                return this.courseScheduledRepository.findByCode(code).map(courseScheduled -> {
                        var classroom = courseScheduled.getClassroom();
                        var course = courseScheduled.getCourse();
                        var enrollment = courseScheduled.getEnrollment();
                        var teacherCourseScheduleds = courseScheduled.getTeacherCourseScheduleds();
                        var contents = courseScheduled.getContentList();
                        return CursoDTO.builder()
                                        .codigo(courseScheduled.getCode())
                                        .nombre(course.getName() + "-" + enrollment.getSeason().getYear())
                                        .numeroSalon(classroom.getNumber())
                                        .piso(classroom.getFloor())
                                        .horaInicio(courseScheduled.getStartTime())
                                        .horaFin(courseScheduled.getEndTime())
                                        .dia(courseScheduled.getDayOfWeek().getDisplayName())
                                        .profesores(teacherCourseScheduleds.stream()
                                                        .map(teacherCourseScheduled -> {
                                                                var teacher = teacherCourseScheduled.getTeacher();
                                                                return teacher.getName() + " "
                                                                                + teacher.getSurnamePaternal() + " "
                                                                                + teacher.getSurnameMaternal();
                                                        }).toList())
                                        .portada(courseScheduled.getPortada())
                                        .numeroSesiones((int) contents.stream()
                                                        .filter(content -> content.getType().equals("session"))
                                                        .count())
                                        .contenidos(contents.stream()
                                                        .filter(Content::isVisible)
                                                        .map(content -> ContentDTO.builder()
                                                                        .nombre(content.getName())
                                                                        .numero(content.getNumber())
                                                                        .tipo(content.getType())
                                                                        .recursos(content.getResourceList().stream()
                                                                                        .map(resource -> ResourceDTO
                                                                                                        .builder()
                                                                                                        .nombre(resource.getName())
                                                                                                        .descripcion(resource
                                                                                                                        .getDescription())
                                                                                                        .tipo(resource.getType())
                                                                                                        .items(resource.getContentItems()
                                                                                                                        .stream()
                                                                                                                        .map(item -> ItemDTO
                                                                                                                                        .builder()
                                                                                                                                        .dniPerson(item.getPerson()
                                                                                                                                                        .getDni())
                                                                                                                                        .tipo(item.getType())
                                                                                                                                        .contenido(item.getContent())
                                                                                                                                        .nombreArchivo(item
                                                                                                                                                        .getName())
                                                                                                                                        .build())
                                                                                                                        .toList())
                                                                                                        .build())
                                                                                        .toList())
                                                                        .build())
                                                        .toList())
                                        .build();
                });
        }

        @Override
        @Transactional(readOnly = true)
        public Promedios calcularPromedios(Enrollment enrollment, Student student) {
                // datos
                List<EnrollmentPeriod> enrollmentPeriods = enrollment.getEnrollmentPeriods();
                List<CourseScheduled> cursosProgramados = enrollment.getCourseScheduleds();
                List<Promedio> promedioList = cursosProgramados.stream()
                                .map(courseScheduled -> {
                                        List<Integer> promediosCursos = new ArrayList<>();
                                        enrollmentPeriods.forEach(enrollmentPeriod -> {
                                                if (enrollmentPeriod.isVisibilityDate()) {
                                                        var period = enrollmentPeriod.getPeriod();
                                                        // tareas dentro de rango de un periodo
                                                        List<Homework> homeworkList = activityService
                                                                        .getActivitiesByTypeAndDateRange(
                                                                                        courseScheduled, Homework.class,
                                                                                        period.getStartDate(),
                                                                                        period.getEndtDate());
                                                        List<Forum> forumList = activityService
                                                                        .getActivitiesByTypeAndDateRange(
                                                                                        courseScheduled, Forum.class,
                                                                                        period.getStartDate(),
                                                                                        period.getEndtDate());
                                                        List<DailyExam> dailyExamList = activityService
                                                                        .getActivitiesByTypeAndDateRange(
                                                                                        courseScheduled,
                                                                                        DailyExam.class,
                                                                                        period.getStartDate(),
                                                                                        period.getEndtDate());
                                                        List<WeeklyExam> weeklyExamList = activityService
                                                                        .getActivitiesByTypeAndDateRange(
                                                                                        courseScheduled,
                                                                                        WeeklyExam.class,
                                                                                        period.getStartDate(),
                                                                                        period.getEndtDate());
                                                        List<MonthlyExam> monthlyExamList = activityService
                                                                        .getActivitiesByTypeAndDateRange(
                                                                                        courseScheduled,
                                                                                        MonthlyExam.class,
                                                                                        period.getStartDate(),
                                                                                        period.getEndtDate());
                                                        List<ExamFinal> examFinalList = activityService
                                                                        .getActivitiesByTypeAndDateRange(
                                                                                        courseScheduled,
                                                                                        ExamFinal.class,
                                                                                        period.getStartDate(),
                                                                                        period.getEndtDate());
                                                        promediosCursos.add(gradeActivityService.promedioGeneral(
                                                                        homeworkList,
                                                                        forumList,
                                                                        dailyExamList,
                                                                        weeklyExamList,
                                                                        monthlyExamList,
                                                                        examFinalList,
                                                                        student.getIdPerson(),
                                                                        enrollment.getTypePeriod().getDisplayName()));
                                                } else {
                                                        promediosCursos.add(null);
                                                }
                                        });
                                        return Promedio.builder()
                                                        .nombre(courseScheduled.getCourse().getName())
                                                        .codigo(courseScheduled.getCode())
                                                        .promedios(promediosCursos)
                                                        .build();
                                }).toList();
                List<String> periods = new ArrayList<>();
                for (int i = 0; i < enrollmentPeriods.size(); i++) {
                        periods.add(enrollmentPeriods.get(i).getEnrollment().getTypePeriod().getDisplayName() + " "
                                        + (i + 1));
                }
                return Promedios.builder()
                                .periodos(periods)
                                .promedioList(promedioList)
                                .build();
        }

        @Override
        @Transactional(readOnly = true)
        public Optional<CursoDTO> findByCodeByStudent(String code, Student student) {
                var courseScheduled = this.courseScheduledRepository.findByCode(code);
                return courseScheduled.map(courseS -> {
                        var classroom = courseS.getClassroom();
                        var course = courseS.getCourse();
                        var enrollment = courseS.getEnrollment();
                        var teacherCourseScheduleds = courseS.getTeacherCourseScheduleds();
                        var contents = courseS.getContentList();
                        return CursoDTO.builder()
                                        .codigo(courseS.getCode())
                                        .nombre(course.getName() + "-" + enrollment.getSeason().getYear())
                                        .numeroSalon(classroom.getNumber())
                                        .piso(classroom.getFloor())
                                        .horaInicio(courseS.getStartTime())
                                        .horaFin(courseS.getEndTime())
                                        .dia(courseS.getDayOfWeek().getDisplayName())
                                        .profesores(teacherCourseScheduleds.stream()
                                                        .map(teacherCourseScheduled -> {
                                                                var teacher = teacherCourseScheduled.getTeacher();
                                                                return teacher.getName() + " "
                                                                                + teacher.getSurnamePaternal() + " "
                                                                                + teacher.getSurnameMaternal();
                                                        }).toList())
                                        .portada(courseS.getPortada())
                                        .numeroSesiones((int) contents.stream()
                                                        .filter(content -> content.getType().equals("session"))
                                                        .count())
                                        .contenidos(contents.stream()
                                                        .filter(Content::isVisible)
                                                        .map(content -> ContentDTO.builder()
                                                                        .nombre(content.getName())
                                                                        .numero(content.getNumber())
                                                                        .tipo(content.getType())
                                                                        .recursos(buildResources(content, student))
                                                                        .build())
                                                        .toList())
                                        .build();
                });
        }

        @Override
        @Transactional(readOnly = true)
        public ResponseEntity<?> findStudents(CursoDTO courseScheduled) {
                final var students = this.studentRepository.findStudentsByCourse(courseScheduled.getCodigo());

                return ResponseEntity.ok(students.stream().map(StudentCourseDTO::buildByStudent).toList());
        }

        private final ICourseScheduledRepository courseScheduledRepository;
        private final IStudentRepository studentRepository;
        private final ActivityService activityService;
        private final GradeActivityService gradeActivityService;

        public CourseScheduledService(ICourseScheduledRepository courseScheduledRepository,
                        ActivityService activityService, GradeActivityService gradeActivity,
                        IStudentRepository studentRepository) {
                this.courseScheduledRepository = courseScheduledRepository;
                this.activityService = activityService;
                this.gradeActivityService = gradeActivity;
                this.studentRepository = studentRepository;
        }

        private List<ResourceDTO> buildResources(Content content, Student student) {
                return content.getResourceList().stream()
                                .map(resource -> ResourceDTO.buildByResource(resource, student))
                                .toList();
        }

}
