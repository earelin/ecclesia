package org.ecclesia.directory.domain;

public class Group {

  private long id;
  private long organization;
  private String name;
  private long parent;

  public boolean isNew() {
    return id == 0;
  }

  public boolean hasParent() {
    return parent != 0;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getOrganization() {
    return organization;
  }

  public void setOrganization(long organization) {
    this.organization = organization;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getParent() {
    return parent;
  }

  public void setParent(long parent) {
    this.parent = parent;
  }
}
