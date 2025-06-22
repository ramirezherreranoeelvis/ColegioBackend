package com.exam.colegio.model.course.content.resource;

import com.exam.colegio.model.course.content.*;
import com.exam.colegio.model.course.content.item.ContentItem;
import com.exam.colegio.model.course.content.resource.activity.Forum;
import com.exam.colegio.model.course.content.resource.activity.Homework;
import com.exam.colegio.model.course.content.resource.data.Data;
import com.exam.colegio.util.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(
        name = "typeResource",
        discriminatorType = DiscriminatorType.STRING,
        length = 20
)
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
@Table(name = "resource")
public abstract class Resource {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idResource;

        @ManyToOne
        @JoinColumn(name = "idContent", nullable = false)
        private Content content;

        @Column(name = "name", nullable = false, length = 100)
        private String name;

        @Column(name = "description", nullable = false, length = 300)
        private String description;

        @Enumerated(EnumType.STRING)
        @Column(name = "permission", length = 10, nullable = false, columnDefinition = "VARCHAR(10)")
        private Permission permission;

        @Column(name = "createdAt")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;

        @JsonIgnore
        @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<ContentItem> contentItems = new ArrayList<>();

        public String getType() {
                if (this instanceof Homework) {
                        return "homework";
                } else if (this instanceof Forum) {
                        return "forum";
                } else if (this instanceof Data) {
                        return "data";
                } else {
                        return "unknown";
                }
        }

}
