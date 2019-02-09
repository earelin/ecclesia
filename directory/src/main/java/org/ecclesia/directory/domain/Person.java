package org.ecclesia.directory.domain;

import javax.persistence.*;
import lombok.Data;

/**
 * Represents one person. Data transfer object.
 */
@Entity
@Table(name = "person")
@Data
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "organization_id")
  private Organization organization;

  private String name;
  private String surname;
  private String email;

  @Embedded
  private Location location;

}
