package com.exam.colegio.model.course.content.resource.activity;

import com.exam.colegio.model.course.content.Content;
import com.exam.colegio.util.Permission;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("homework")
public class Homework extends Activity {

        @Builder
        public Homework(int idResource, Content content, String name, String description, Permission permission, Date createdAt, Date dueDate) {
                super(idResource, content, name, description, permission, createdAt, dueDate);
        }

}
