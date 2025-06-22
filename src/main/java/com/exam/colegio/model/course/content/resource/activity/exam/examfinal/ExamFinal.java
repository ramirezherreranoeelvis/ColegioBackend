package com.exam.colegio.model.course.content.resource.activity.exam.examfinal;

import com.exam.colegio.model.course.content.Content;
import com.exam.colegio.model.course.content.resource.activity.Activity;
import com.exam.colegio.util.Permission;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("exam_final")
public class ExamFinal extends Activity {

        @Builder
        public ExamFinal(int idResource, Content content, String name, String description, Permission permission, Date createdAt, Date dueDate) {
                super(idResource, content, name, description, permission, createdAt, dueDate);
        }

}
