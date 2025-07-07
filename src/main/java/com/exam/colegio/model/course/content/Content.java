package com.exam.colegio.model.course.content;

import com.exam.colegio.model.course.CourseScheduled;
import com.exam.colegio.model.course.content.resource.Resource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(
        name = "typeContent",
        discriminatorType = DiscriminatorType.STRING,
        length = 100
)
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
@Table(name = "content")
public abstract class Content {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idContent;

        @Column(name = "name", nullable = true, length = 250)
        private String name;

        @Column(name = "number", nullable = false)
        private int number;

        @Column(name = "isVisible", nullable = false)
        private boolean isVisible;

        @ManyToOne
        @JoinColumn(name = "idCourseScheduled", nullable = false)
        private CourseScheduled courseScheduled;

        @JsonIgnore
        @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<Resource> resourceList = new ArrayList<>();

        public String getType() {
                if (this instanceof AuxiliaryContent) {
                        return "auxiliary";
                } else if (this instanceof ExtraContent) {
                        return "extra";
                } else if (this instanceof GeneralContent) {
                        return "general";
                } else if (this instanceof SessionContent) {
                        return "session";
                } else {
                        return "unknown";
                }
        }

}
