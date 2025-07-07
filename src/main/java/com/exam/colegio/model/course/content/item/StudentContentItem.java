package com.exam.colegio.model.course.content.item;

import com.exam.colegio.model.course.content.resource.Resource;
import com.exam.colegio.model.person.Person;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("studentContent")
public class StudentContentItem extends ContentItem {

        @Builder
        public StudentContentItem(int idContentItem, Person person, Resource resource, String content, String name) {
                super(idContentItem, person, resource, content, name);
        }

}
