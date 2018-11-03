package org.ecclesia.directory.entity.converter;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.entity.LocationDto;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationMapperTest {

  private LocationMapper locationMapper;

  @Before
  public void init() {
    locationMapper = new LocationMapperImpl();
  }

  @Test
  public void testDtoToDomain() {
    LocationDto locationDto = new LocationDto();
    locationDto.setAddress1("66");
    locationDto.setAddress2("Avonmore Road");
    locationDto.setCountry("GB");
    locationDto.setPostcode("W14 8RS");
    locationDto.setTown("London");

    Location location = locationMapper.dtoToDomain(locationDto);

    assertThat(location.getAddress1()).isEqualTo(locationDto.getAddress1());
    assertThat(location.getAddress2()).isEqualTo(locationDto.getAddress2());
    assertThat(location.getCountry()).isEqualTo(locationDto.getCountry());
    assertThat(location.getPostcode()).isEqualTo(locationDto.getPostcode());
    assertThat(location.getTown()).isEqualTo(locationDto.getTown());

    location = locationMapper.dtoToDomain(null);

    assertThat(location).isNull();
  }

  @Test
  public void testDomainToDto() {
    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    LocationDto locationDto = locationMapper.domainToDto(location);

    assertThat(locationDto.getAddress1()).isEqualTo(location.getAddress1());
    assertThat(locationDto.getAddress2()).isEqualTo(location.getAddress2());
    assertThat(locationDto.getCountry()).isEqualTo(location.getCountry());
    assertThat(locationDto.getPostcode()).isEqualTo(location.getPostcode());
    assertThat(locationDto.getTown()).isEqualTo(location.getTown());

    locationDto = locationMapper.domainToDto(null);

    assertThat(locationDto).isNull();
  }

}
