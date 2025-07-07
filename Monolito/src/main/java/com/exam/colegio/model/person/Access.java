package com.exam.colegio.model.person;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "access", indexes = {
      @Index(name = "idx_username", columnList = "username")
})
public class Access {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int idAccess;

      @Column(name = "access", nullable = false)
      private boolean accessEnabled;

      @Column(name = "username", nullable = false, unique = true, updatable = false, length = 20)
      private String username;

      @Column(name = "password", nullable = false, length = 20)
      private String password;

      @Column(name = "description", length = 150)
      private String description;

      @OneToOne(mappedBy = "access")
      private Person person;
}
