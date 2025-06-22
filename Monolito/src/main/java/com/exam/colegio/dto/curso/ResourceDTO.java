package com.exam.colegio.dto.curso;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

import com.exam.colegio.model.course.content.resource.Resource;
import com.exam.colegio.model.course.content.resource.activity.Activity;
import com.exam.colegio.model.person.Student;
import com.exam.colegio.util.DateFormatUtil;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ResourceDTO {

        private String nombre;
        private String descripcion;
        private String tipo;
        private List<ItemDTO> items;
        private List<NotaDTO> notas;

        public static ResourceDTO buildByResource(Resource resource, Student student) {
                var resourceDTO = ResourceDTO.builder()
                                .nombre(resource.getName())
                                .descripcion(resource.getDescription())
                                .tipo(resource.getType())
                                .items(buildItems(resource, student))
                                .build();
                if (!resource.getType().equals("data")) {
                        resourceDTO.setNotas(getNotaForStudent((Activity) resource, student));
                }
                return resourceDTO;
        }

        private static List<ItemDTO> buildItems(Resource resource, Student student) {
                return resource.getContentItems().stream()
                                .filter(contentItem -> contentItem.getType().equals("teacherContent")
                                                || (contentItem.getType().equals("studentContent") && contentItem
                                                                .getPerson().getDni().equals(student.getDni())))
                                .map(item -> ItemDTO.builder()
                                                .dniPerson(item.getPerson().getDni())
                                                .tipo(item.getType())
                                                .contenido(item.getContent())
                                                .nombreArchivo(item.getName())
                                                .build())
                                .toList();
        }

        private static List<NotaDTO> getNotaForStudent(Activity activity, Student student) {
                return activity.getGradeActivity().stream()
                                .filter(gradeActivity -> gradeActivity.getPerson().getDni().equals(student.getDni()))
                                .map(gradeActivity -> NotaDTO.builder()
                                                .comentario(gradeActivity.getComments())
                                                .fechaCalificacion(DateFormatUtil.ymd(gradeActivity.getGradedAt()))
                                                .nota(gradeActivity.getGradeValue())
                                                .build())
                                .collect(Collectors.toList());
        }
}
