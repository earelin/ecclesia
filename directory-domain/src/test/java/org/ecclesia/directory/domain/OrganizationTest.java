package org.ecclesia.directory.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class OrganizationTest {

  private Organization organization;

  @Before
  public void init() {
    organization = new Organization();
  }

  @Test
  public void testIsNew() {
    assertThat(organization.isNew()).isTrue();

    organization.setId(1);
    assertThat(organization.isNew()).isFalse();
  }

}
