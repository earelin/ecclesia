package org.ecclesia.directory.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Group {

  private String id;
  private long organization;
  private String name;
  private long parent;

}
