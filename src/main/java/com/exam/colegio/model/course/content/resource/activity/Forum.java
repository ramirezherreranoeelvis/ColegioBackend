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
@DiscriminatorValue("forum")
public class Forum extends Activity {

        @Builder
        public Forum(int idResource, Content content, String name, String description, Permission permission, Date createdAt, Date dueDate) {
                super(idResource, content, name, description, permission, createdAt, dueDate);
        }
}
