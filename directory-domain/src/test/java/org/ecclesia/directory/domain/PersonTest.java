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

  @Test
  public void testId() {
    assertThat(person.getId()).isEqualTo(0);
    person.setId(1);
    assertThat(person.getId()).isEqualTo(1);
  }

  @Test
  public void testOrganization() {
    assertThat(person.getOrganization()).isEqualTo(0);
    person.setOrganization(1);
    assertThat(person.getOrganization()).isEqualTo(1);
  }

  @Test
  public void testName() {
    assertThat(person.getName()).isNull();
    person.setName("John");
    assertThat(person.getName()).isEqualTo("John");
  }

  @Test
  public void testSurname() {
    assertThat(person.getSurname()).isNull();
    person.setSurname("Smith");
    assertThat(person.getSurname()).isEqualTo("Smith");
  }

  @Test
  public void testEmail() {
    assertThat(person.getEmail()).isNull();
    person.setEmail("john.smith@gmail.com");
    assertThat(person.getEmail()).isEqualTo("john.smith@gmail.com");
  }

  @Test
  public void testLocation() {
    assertThat(person.getLocation()).isNull();

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");
    person.setLocation(location);

    Location returnedLocation = person.getLocation();
    assertThat(returnedLocation.getAddress1()).isEqualTo("66");
    assertThat(returnedLocation.getAddress2()).isEqualTo("Avonmore Road");
    assertThat(returnedLocation.getCountry()).isEqualTo("GB");
    assertThat(returnedLocation.getPostcode()).isEqualTo("W14 8RS");
    assertThat(returnedLocation.getTown()).isEqualTo("London");
  }

}
