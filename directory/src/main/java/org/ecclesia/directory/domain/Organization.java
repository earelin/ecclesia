package org.ecclesia.directory.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an organization. Data transfer object.
 */
@Entity
@Table(name = "organization")
@Getter @Setter @NoArgsConstructor
public class Organization implements Serializable {

  private static final long serialVersionUID = 719423610411393862L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank
  private String name;

}
