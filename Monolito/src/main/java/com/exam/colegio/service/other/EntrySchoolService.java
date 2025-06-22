package com.exam.colegio.service.other;

import com.exam.colegio.dao.other.IEntrySchoolDAO;
import com.exam.colegio.dto.historial.DiaIngreso;
import com.exam.colegio.dto.historial.EventDTO;
import com.exam.colegio.dto.historial.SemanaDTO;
import com.exam.colegio.model.other.EntrySchool;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.repository.other.IEntrySchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class EntrySchoolService implements IEntrySchoolDAO {

        @Override
        public List<SemanaDTO> finAllSemanaDTOByStudent(Student student) {
                var entradas = this.entrySchoolRepository.findAllByStudent(student.getDni());
                rellenarDiasIntermedios(entradas);
                return castListSemanaDTO(entradas);
        }

        private void rellenarDiasIntermedios(List<EntrySchool> list) {
                List<EntrySchool> newEntries = new ArrayList<>();

                // Agrupar por semana
                list.stream().collect(Collectors.groupingBy(entry -> {
                        LocalDate date = entry.getTimeEntry().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        return date.with(DayOfWeek.MONDAY);
                })).forEach((weekStart, entries) -> {
                        // Crear un conjunto de días presentes en la semana
                        List<DayOfWeek> daysPresent = entries.stream().map(entry -> entry.getTimeEntry().toInstant().atZone(ZoneId.systemDefault()).getDayOfWeek()).collect(Collectors.toList());

                        // Rellenar los días faltantes
                        for (DayOfWeek day : DayOfWeek.values()) {
                                if (!daysPresent.contains(day)) {
                                        try {
                                                LocalDate date = weekStart.plusDays(day.getValue() - 1);
                                                Date timeEntry = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
                                                newEntries.add(EntrySchool.builder().timeEntry(timeEntry).build());
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                });

                list.addAll(newEntries);
                list.sort((e1, e2) -> e1.getTimeEntry().compareTo(e2.getTimeEntry()));
        }

        private List<SemanaDTO> castListSemanaDTO(List<EntrySchool> list) {
                //datos:
                List<SemanaDTO> semanaDTOS = new ArrayList<>();
                List<EntrySchool[]> semanas = new ArrayList<>();

                //separacion por semanas:
                BiFunction<List<EntrySchool>, List<EntrySchool[]>, List<EntrySchool[]>> separacionPorSemanas = (x, y) -> {
                        var semana = new EntrySchool[7];
                        int nDiaSemana = 0;
                        for (var entradas : x) {
                                semana[nDiaSemana] = entradas;
                                nDiaSemana++;
                                if (nDiaSemana == 7) {
                                        nDiaSemana = 0;
                                        y.add(semana);
                                        semana = new EntrySchool[7];
                                }
                        }
                        return y;
                };
                semanas = separacionPorSemanas.apply(list, semanas);

                //conversion de arreglos uno por uno arreglo convirtiendo:
                semanas.forEach(semana -> semanaDTOS.add(castSemanaDTO(semana)));

                //en reversa la lista de semanas:
                Collections.reverse(semanaDTOS);

                //poner nomrbes a las semanas
                int nSemana = 0;
                for (var semana : semanaDTOS) {
                        nSemana++;
                        if (nSemana == 1) {
                                semana.setName("Semana Actual");
                        } else if (nSemana == 2) {
                                semana.setName("Semana Pasada");
                        } else if (nSemana == 3) {
                                semana.setName("Semana AntePasada");
                        } else {
                                semana.setName("Hace " + nSemana + " Semanas");
                        }
                }

                //resultado:
                return semanaDTOS;
        }

        private SemanaDTO castSemanaDTO(EntrySchool[] entrySchools) {
                SemanaDTO semanaDTO = new SemanaDTO();
                List<DiaIngreso> dias = new ArrayList<>();

                int nDia = 0;
                var nameDias = new String[]{"lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo"};
                // Recorrer el arreglo de EntrySchool
                for (EntrySchool entry : entrySchools) {
                        DiaIngreso dia = new DiaIngreso();
                        //ponemos nombre del dia
                        dia.setName(nameDias[nDia]);

                        // Verificar si el idEntrySchool es 0
                        if (entry.getIdEntrySchool() != 0) {
                                dia.setEntrada(convertirEvento(entry.getTimeEntry(), "asistio"));
                                if (entry.getTimeExit() != null) {
                                        dia.setSalida(convertirEvento(entry.getTimeExit(), "asistio"));
                                } else {
                                        dia.setSalida(convertirEvento(entry.getTimeEntry(), "falto"));
                                }

                        } else {
                                dia.setEntrada(convertirEvento(entry.getTimeEntry(), ""));
                                dia.setSalida(convertirEvento(entry.getTimeEntry(), ""));
                        }

                        dia.setElementoFinal(false); // Ajustar según sea necesario
                        dias.add(dia);

                        //incrementamos dia y si es el ultimo vuelve al 0 que es lunes
                        nDia++;
                        if (nDia == 7) {
                                nDia = 0;
                        }
                }

                // Establecer el último elemento como elementoFinal
                if (!dias.isEmpty()) {
                        dias.get(dias.size() - 1).setElementoFinal(true);
                }

                semanaDTO.setDias(dias);
                return semanaDTO;
        }

        private EventDTO convertirEvento(Date fecha, String status) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                EventDTO evento = new EventDTO();
                evento.setContent(formato.format(fecha));
                evento.setStatus(status);
                return evento;
        }

        private final IEntrySchoolRepository entrySchoolRepository;

        @Autowired
        public EntrySchoolService(IEntrySchoolRepository entrySchoolRepository) {
                this.entrySchoolRepository = entrySchoolRepository;
        }

}
