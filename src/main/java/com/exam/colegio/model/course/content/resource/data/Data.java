package com.exam.colegio.model.course.content.resource.data;

import com.exam.colegio.model.course.content.Content;
import com.exam.colegio.model.course.content.resource.Resource;
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
@DiscriminatorValue("data")
public class Data extends Resource {

        @Builder
        public Data(int idResource, Content content, String name, String description, Permission permission, Date createdAt) {
                super(idResource, content, name, description, permission, createdAt);
        }

}
