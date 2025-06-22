package com.exam.colegio.service.course.content.resource;

import com.exam.colegio.model.course.content.resource.activity.Forum;
import com.exam.colegio.model.course.content.resource.activity.Homework;
import com.exam.colegio.model.course.content.resource.activity.exam.DailyExam;
import com.exam.colegio.model.course.content.resource.activity.exam.MonthlyExam;
import com.exam.colegio.model.course.content.resource.activity.exam.WeeklyExam;
import com.exam.colegio.model.course.content.resource.activity.exam.examfinal.ExamFinal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeActivityService {

        public int promedioGeneral(List<Homework> homeworkList, List<Forum> forumList, List<DailyExam> dailyExamList, List<WeeklyExam> weeklyExamList, List<MonthlyExam> monthlyExamList, List<ExamFinal> examFinalList, int idStudent, String typePeriod) {
                /*
                 * Variables:
                 * nT: Promedio de notas de tareas.
                 * nF: Promedio de notas de foros.
                 * nED: Promedio de notas de exámenes diarios.
                 * nES: Promedio de notas de exámenes semanales.
                 * nEM: Promedio de notas de exámenes mensuales.
                 * nEF: Nota del examen final (bimestral, trimestral o semestral).
                 * typePeriod : Tipo de periodo
                 */
                var nT = (int) Math.ceil(promedioTarea(homeworkList, idStudent));
                var nF = (int) Math.ceil(promedioForo(forumList, idStudent));
                var nED = (int) Math.ceil(promedioExamenesDiarios(dailyExamList, idStudent));
                var nEM = (int) Math.ceil(promedioExamenesMensuales(monthlyExamList, idStudent));
                var nES = (int) Math.ceil(promedioExamenesSemanales(weeklyExamList, idStudent));
                int nEF = (int) Math.ceil(notaExamenFinal(examFinalList, idStudent));

                double pT, pF, pED, pEM, pES, pEF;
                switch (typePeriod) {
                        case "BIMESTRE" -> {
                                pT = 0.1;
                                pF = 0.1;
                                pED = 0.05;
                                pEM = 0.15;
                                pES = 0.1;
                                pEF = 0.5;
                        }
                        case "TRIMESTRE" -> {
                                pT = 0.1;
                                pF = 0.1;
                                pED = 0.05;
                                pEM = 0.1;
                                pES = 0.1;
                                pEF = 0.55;
                        }
                        case "SEMESTRE" -> {
                                pT = 0.1;
                                pF = 0.1;
                                pED = 0.05;
                                pEM = 0.1;
                                pES = 0.1;
                                pEF = 0.55;
                        }

                        default -> throw new IllegalArgumentException("Tipo de periodo no válido: " + typePeriod);
                }


                return (int) Math.ceil(
                        (pT * nT) +
                        (pF * nF) +
                        (pED * nED) +
                        (pEM * nEM) +
                        (pES * nES) +
                        (pEF * nEF)
                );
        }

        public double promedioTarea(List<Homework> tareas, int idStudent) {
                var sumaTotalNotasTareas = tareas.stream()
                        .mapToDouble(homework -> {
                                var gradeActivityList = homework.getGradeActivity()
                                        .stream()
                                        .filter(gradeActivity -> gradeActivity != null)
                                        .collect(Collectors.toList());

                                var gradeList = gradeActivityList.stream()
                                        .filter(gradeActivity -> gradeActivity.getPerson().getIdPerson() == idStudent)
                                        .findFirst().orElse(null);

                                if (gradeList == null) {
                                        return 0;
                                }
                                return gradeList.getGradeValue().doubleValue();

                        })
                        .sum();
                return sumaTotalNotasTareas / tareas.size();
        }

        public double notaExamenFinal(List<ExamFinal> examFinalList, int idStudent) {
                var sumaTotalNotasTareas = examFinalList.stream()
                        .mapToDouble(bimonthlyExam -> {
                                var gradeActivityList = bimonthlyExam.getGradeActivity()
                                        .stream()
                                        .filter(gradeActivity -> gradeActivity != null)
                                        .collect(Collectors.toList());

                                var gradeList = gradeActivityList.stream()
                                        .filter(gradeActivity -> gradeActivity.getPerson().getIdPerson() == idStudent)
                                        .findFirst().orElse(null);

                                if (gradeList == null) {
                                        return 0;
                                }
                                return gradeList.getGradeValue().doubleValue();

                        })
                        .sum();
                return sumaTotalNotasTareas / examFinalList.size();
        }

        public double promedioForo(List<Forum> foros, int idStudent) {
                var sumaTotalNotasTareas = foros.stream()
                        .mapToDouble(homework -> {
                                var gradeActivityList = homework.getGradeActivity()
                                        .stream()
                                        .filter(gradeActivity -> gradeActivity != null)
                                        .collect(Collectors.toList());

                                var gradeList = gradeActivityList.stream()
                                        .filter(gradeActivity -> gradeActivity.getPerson().getIdPerson() == idStudent)
                                        .findFirst().orElse(null);

                                if (gradeList == null) {
                                        return 0;
                                }
                                return gradeList.getGradeValue().doubleValue();

                        })
                        .sum();
                return sumaTotalNotasTareas / foros.size();
        }

        public double promedioExamenesDiarios(List<DailyExam> examenesDiarios, int idStudent) {
                var sumaTotalNotasTareas = examenesDiarios.stream()
                        .mapToDouble(homework -> {
                                var gradeActivityList = homework.getGradeActivity()
                                        .stream()
                                        .filter(gradeActivity -> gradeActivity != null)
                                        .collect(Collectors.toList());

                                var gradeList = gradeActivityList.stream()
                                        .filter(gradeActivity -> gradeActivity.getPerson().getIdPerson() == idStudent)
                                        .findFirst().orElse(null);

                                if (gradeList == null) {
                                        return 0;
                                }
                                return gradeList.getGradeValue().doubleValue();

                        })
                        .sum();
                return sumaTotalNotasTareas / examenesDiarios.size();
        }

        public double promedioExamenesSemanales(List<WeeklyExam> examenesSemanales, int idStudent) {
                var sumaTotalNotasTareas = examenesSemanales.stream()
                        .mapToDouble(homework -> {
                                var gradeActivityList = homework.getGradeActivity()
                                        .stream()
                                        .filter(gradeActivity -> gradeActivity != null)
                                        .collect(Collectors.toList());

                                var gradeList = gradeActivityList.stream()
                                        .filter(gradeActivity -> gradeActivity.getPerson().getIdPerson() == idStudent)
                                        .findFirst().orElse(null);

                                if (gradeList == null) {
                                        return 0;
                                }
                                return gradeList.getGradeValue().doubleValue();

                        })
                        .sum();
                return sumaTotalNotasTareas / examenesSemanales.size();
        }

        public double promedioExamenesMensuales(List<MonthlyExam> examenesMensuales, int idStudent) {
                var sumaTotalNotasTareas = examenesMensuales.stream()
                        .mapToDouble(homework -> {
                                var gradeActivityList = homework.getGradeActivity()
                                        .stream()
                                        .filter(gradeActivity -> gradeActivity != null)
                                        .collect(Collectors.toList());

                                var gradeList = gradeActivityList.stream()
                                        .filter(gradeActivity -> gradeActivity.getPerson().getIdPerson() == idStudent)
                                        .findFirst().orElse(null);

                                if (gradeList == null) {
                                        return 0;
                                }
                                return gradeList.getGradeValue().doubleValue();

                        })
                        .sum();
                return sumaTotalNotasTareas / examenesMensuales.size();
        }


}
