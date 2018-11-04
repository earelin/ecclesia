package org.ecclesia.directory.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Represents an organization. Data transfer object.
 */
@Entity
@Table(name = "organization")
public class OrganizationDto implements Serializable {

  private static final long serialVersionUID = 719423610411393862L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank
  private String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
