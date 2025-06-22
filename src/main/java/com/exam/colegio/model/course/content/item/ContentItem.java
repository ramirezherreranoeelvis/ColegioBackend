package com.exam.colegio.model.course.content.item;

import com.exam.colegio.model.course.content.resource.Resource;
import com.exam.colegio.model.person.Person;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(
        name = "typeContentItem",
        discriminatorType = DiscriminatorType.STRING,
        length = 100
)
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
@Table(name = "contentItem")
public abstract class ContentItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idContentItem;

        @ManyToOne
        @JoinColumn(name = "idPerson", nullable = false)
        private Person person;

        @ManyToOne
        @JoinColumn(name = "idResource", nullable = false)
        private Resource resource;

        @Column(name = "content", nullable = false, columnDefinition = "LONGTEXT")
        private String content;

        @Column(name = "name", columnDefinition = "LONGTEXT")
        private String name;

        public String getType() {
                if (this instanceof TeacherContentItem) {
                        return "teacherContent";
                } else if (this instanceof StudentContentItem) {
                        return "studentContent";
                } else {
                        return "unknown";
                }
        }

}
