package org.ecclesia.directory.domain;

import lombok.Data;

@Data
public class Location {

  private String address1;
  private String address2;
  private String town;
  private String country;
  private String postcode;

}
