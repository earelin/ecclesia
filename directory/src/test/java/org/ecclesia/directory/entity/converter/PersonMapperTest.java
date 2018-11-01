package org.ecclesia.directory.entity.converter;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.entity.LocationDto;
import org.ecclesia.directory.entity.PersonDto;
import org.ecclesia.directory.service.OrganizationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PersonMapperImpl.class, LocationMapperImpl.class, OrganizationServiceImpl.class})
public class PersonMapperTest {

  @Autowired
  private PersonMapper personMapper;

  @Test
  public void testDtoToDomain() {
    LocationDto locationDto = new LocationDto();
    locationDto.setAddress1("66");
    locationDto.setAddress2("Avonmore Road");
    locationDto.setCountry("GB");
    locationDto.setPostcode("W14 8RS");
    locationDto.setTown("London");

    PersonDto personDto = new PersonDto();
    personDto.setEmail("john.smith@company.com");
    personDto.setLocation(locationDto);
    personDto.setName("John");
    personDto.setSurname("Smith");

    Person person = personMapper.dtoToDomain(personDto);

    assertThat(person.getEmail()).isEqualTo(personDto.getEmail());
    assertThat(person.getName()).isEqualTo(personDto.getName());
    assertThat(person.getSurname()).isEqualTo(personDto.getSurname());

    person = personMapper.dtoToDomain(null);

    assertThat(person).isNull();
  }

  @Test
  public void testDomainToDto() {
    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Person person = new Person();
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    PersonDto personDto = personMapper.domainToDto(person);

    assertThat(personDto.getEmail()).isEqualTo(person.getEmail());
    assertThat(personDto.getName()).isEqualTo(person.getName());
    assertThat(personDto.getSurname()).isEqualTo(person.getSurname());

    personDto = personMapper.domainToDto(null);

    assertThat(personDto).isNull();
  }

}
