package org.ecclesia.directory.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Location {

  private String address1;
  private String address2;
  private String town;
  private String country;
  private String postcode;
}
