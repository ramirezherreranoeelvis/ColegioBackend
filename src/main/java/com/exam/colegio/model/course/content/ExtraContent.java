package com.exam.colegio.model.course.content;

import com.exam.colegio.model.course.CourseScheduled;
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
@DiscriminatorValue("extra")
public class ExtraContent extends Content {

        @Builder
        public ExtraContent(int idContent, String name, int number, boolean isVisible, CourseScheduled courseScheduled) {
                super(idContent, name, number, isVisible, courseScheduled);
        }

}
