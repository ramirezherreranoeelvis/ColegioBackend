package com.exam.colegio.model.course.content.resource.activity.exam.examfinal;

import com.exam.colegio.model.course.content.Content;
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
@DiscriminatorValue("bimonthly_exam")
public class BimonthlyExam  extends ExamFinal {

        public BimonthlyExam (int idResource, Content content, String name, String description, Permission permission, Date createdAt, Date dueDate) {
                super(idResource, content, name, description, permission, createdAt, dueDate);
        }

}
