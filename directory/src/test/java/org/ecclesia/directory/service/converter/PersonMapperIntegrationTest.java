package org.ecclesia.directory.service.converter;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.entity.PersonDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonMapperIntegrationTest {

  @Autowired
  private PersonMapper personMapper;

  @Test
  public void testDtoToDomain() {
    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    PersonDto personDto = new PersonDto();
    personDto.setEmail("john.smith@company.com");
    personDto.setLocation(location);
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

  @Test
  public void testDtoListToDomainList() {
    List<PersonDto> personDtos = new ArrayList<>();

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    PersonDto personDto = new PersonDto();
    personDto.setEmail("john.smith@company.com");
    personDto.setLocation(location);
    personDto.setName("John");
    personDto.setSurname("Smith");

    personDtos.add(personDto);

    location = new Location();
    location.setAddress1("Shakespeare’s Globe");
    location.setAddress2("21 New Globe Walk");
    location.setCountry("GB");
    location.setPostcode("SE1 9DT");
    location.setTown("London");

    personDto = new PersonDto();
    personDto.setEmail("william.shakespeare@theglobe.com");
    personDto.setLocation(location);
    personDto.setName("William");
    personDto.setSurname("Shakespeare");

    personDtos.add(personDto);

    List<Person> persons = personMapper.dtoListToDomainList(personDtos);
    assertThat(persons.size()).isEqualTo(2);
  }

}
