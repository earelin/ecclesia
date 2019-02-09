package org.ecclesia.directory;

import org.ecclesia.directory.domain.Organization;

public class ContentGeneration {

  public static Organization generateOrganization() {
    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    return organization;
  }

}
