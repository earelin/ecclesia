package org.ecclesia.directory.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents one person. Data transfer object.
 */
@Entity
@Table(name = "person")
@Getter @Setter @NoArgsConstructor
public class Person implements Serializable {

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
