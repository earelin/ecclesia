package org.ecclesia.directory.domain;

import lombok.Data;

@Data
public class Group {

  private long id;
  private long organization;
  private String name;
  private long parent;

}
