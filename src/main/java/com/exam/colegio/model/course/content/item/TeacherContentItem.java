package com.exam.colegio.model.course.content.item;

import com.exam.colegio.model.course.content.resource.Resource;
import com.exam.colegio.model.person.Person;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@DiscriminatorValue("teacherContent")
public class TeacherContentItem extends ContentItem{

        @Builder
        public TeacherContentItem(int idContentItem, Person person, Resource resource, String content, String name) {
                super(idContentItem, person, resource, content, name);
        }

}
