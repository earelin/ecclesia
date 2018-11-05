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

  @Test
  public void testId() {
    assertThat(organization.getId()).isEqualTo(0);
    organization.setId(1);
    assertThat(organization.getId()).isEqualTo(1);
  }

  @Test
  public void testName() {
    assertThat(organization.getName()).isNull();
    organization.setName("Greenpeace");
    assertThat(organization.getName()).isEqualTo("Greenpeace");
  }

}
