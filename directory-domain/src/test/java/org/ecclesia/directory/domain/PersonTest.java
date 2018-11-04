package org.ecclesia.directory.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PersonTest {

  private Person person;

  @Before
  public void init() {
    person = new Person();
  }

  @Test
  public void testIsNew() {
    assertThat(person.isNew()).isTrue();

    person.setId(1);
    assertThat(person.isNew()).isFalse();
  }

}
