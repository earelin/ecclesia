package org.ecclesia.directory.entity;

/**
 * Represents an organization. Data transfer object.
 */
public class OrganizationDto {

  private long id;
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
