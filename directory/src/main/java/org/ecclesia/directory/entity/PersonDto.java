package org.ecclesia.directory.entity;

import org.ecclesia.directory.domain.Location;

import javax.persistence.*;

/**
 * Represents one person. Data transfer object.
 */
@Entity
@Table(name = "person")
public class PersonDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "organization_id")
  private OrganizationDto organization;

  private String name;
  private String surname;
  private String email;

  @Embedded
  private Location location;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public OrganizationDto getOrganization() {
    return organization;
  }

  public void setOrganization(OrganizationDto organization) {
    this.organization = organization;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
}
