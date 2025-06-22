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
@DiscriminatorValue("auxiliary")
public class AuxiliaryContent extends Content {

        @Builder
        public AuxiliaryContent(int idContent, String name, int number, boolean isVisible, CourseScheduled courseScheduled) {
                super(idContent, name, number, isVisible, courseScheduled);
        }

}
