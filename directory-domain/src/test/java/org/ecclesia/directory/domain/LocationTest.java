package org.ecclesia.directory.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationTest {

  private Location location;

  @Before
  public void init() {
    location = new Location();
  }

  @Test
  public void testAddress1() {
    assertThat(location.getAddress1()).isNull();
    location.setAddress1("66");
    assertThat(location.getAddress1()).isEqualTo("66");
  }

  @Test
  public void testAddress2() {
    assertThat(location.getAddress2()).isNull();
    location.setAddress2("Avonmore Road");
    assertThat(location.getAddress2()).isEqualTo("Avonmore Road");
  }

  @Test
  public void testTown() {
    assertThat(location.getTown()).isNull();
    location.setTown("London");
    assertThat(location.getTown()).isEqualTo("London");
  }

  @Test
  public void testCountry() {
    assertThat(location.getCountry()).isNull();
    location.setCountry("GB");
    assertThat(location.getCountry()).isEqualTo("GB");
  }

  @Test
  public void testPostcode() {
    assertThat(location.getPostcode()).isNull();
    location.setPostcode("W14 8RS");
    assertThat(location.getPostcode()).isEqualTo("W14 8RS");
  }

}
