package org.ecclesia.directory.domain;

public class Organization {

  private long id;
  private String name;

  public boolean isNew() {
    return id == 0;
  }

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
