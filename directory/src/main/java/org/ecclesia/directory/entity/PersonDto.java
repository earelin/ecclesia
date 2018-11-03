package org.ecclesia.directory.entity;

import org.ecclesia.directory.domain.Organization;

/**
 * Represents one person. Data transfer object.
 */
public class PersonDto {

  private long id;
  private Organization organization;
  private String name;
  private String surname;
  private String email;
  private LocationDto location;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
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

  public LocationDto getLocation() {
    return location;
  }

  public void setLocation(LocationDto location) {
    this.location = location;
  }
}
