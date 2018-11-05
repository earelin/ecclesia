package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.entity.OrganizationDto;
import org.ecclesia.directory.entity.PersonDto;
import org.ecclesia.directory.repository.PersonRepository;
import org.ecclesia.directory.service.converter.PersonMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class PersonServiceImplTest {

  private PersonServiceImpl personService;

  @Mock
  private PersonRepository personRepository;

  @Mock
  private OrganizationService organizationService;

  @Mock
  private PersonMapper personMapper;

  @Before
  public void init() {
    personService = new PersonServiceImpl(personMapper, personRepository, organizationService);
  }

  @Test
  public void testDeleteById() throws EntityDoesNotExists {
    when(personRepository.existsById(1)).thenReturn(true);

    personService.deleteById(1);
    verify(personRepository).deleteById(1);
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testDeleteByIdNotFound() throws EntityDoesNotExists {
    when(personRepository.existsById(1)).thenReturn(false);

    personService.deleteById(1);
    verify(personRepository, never()).deleteById(1);
  }

  @Test
  public void testFindAllByOrganization() throws EntityDoesNotExists {
    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");

    List<PersonDto> personDtos = new ArrayList<>();

    PersonDto personDto = new PersonDto();
    personDto.setOrganization(organizationDto);
    personDto.setName("John");
    personDto.setSurname("Smith");
    personDto.setEmail("john.smith@gmail.com");

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");
    personDto.setLocation(location);

    personDtos.add(personDto);

    personDto = new PersonDto();
    personDto.setEmail("william.shakespeare@theglobe.com");
    personDto.setLocation(location);
    personDto.setName("William");
    personDto.setSurname("Shakespeare");
    personDto.setOrganization(organizationDto);

    location = new Location();
    location.setAddress1("Shakespeare’s Globe");
    location.setAddress2("21 New Globe Walk");
    location.setCountry("GB");
    location.setPostcode("SE1 9DT");
    location.setTown("London");

    personDtos.add(personDto);

    when(personRepository.findAllByOrganizationId(1)).thenReturn(personDtos);

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    List<Person> people = new ArrayList<>();

    Person person = new Person();
    person.setOrganization(organization.getId());
    person.setName("John");
    person.setSurname("Smith");
    person.setEmail("john.smith@gmail.com");

    location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");
    person.setLocation(location);

    people.add(person);

    person = new Person();
    person.setEmail("william.shakespeare@theglobe.com");
    person.setLocation(location);
    person.setName("William");
    person.setSurname("Shakespeare");
    person.setOrganization(organization.getId());

    location = new Location();
    location.setAddress1("Shakespeare’s Globe");
    location.setAddress2("21 New Globe Walk");
    location.setCountry("GB");
    location.setPostcode("SE1 9DT");
    location.setTown("London");

    people.add(person);

    when(personMapper.dtoListToDomainList(personDtos)).thenReturn(people);

    when(organizationService.existsById(1)).thenReturn(true);

    List<Person> returnPeople = personService.findAllByOrganization(1);
    assertThat(returnPeople.size()).isEqualTo(2);
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testFindAllByOrganizationNotFound() throws EntityDoesNotExists {
    when(organizationService.existsById(1)).thenReturn(false);

    personService.findAllByOrganization(1);
    verify(personRepository, never()).findAllByOrganizationId(any());
  }

  @Test
  public void testFindById() throws EntityDoesNotExists {
    when(personRepository.existsById(1)).thenReturn(true);

    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");

    List<PersonDto> personDtos = new ArrayList<>();

    PersonDto personDto = new PersonDto();
    personDto.setId(1);
    personDto.setOrganization(organizationDto);
    personDto.setName("John");
    personDto.setSurname("Smith");
    personDto.setEmail("john.smith@gmail.com");

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");
    personDto.setLocation(location);

    when(personRepository.findById(1)).thenReturn(personDto);

    Person person = new Person();
    person.setId(1);
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    when(personMapper.dtoToDomain(personDto)).thenReturn(person);

    Person returnedPerson = personService.findById(1);
    assertThat(returnedPerson).isNotNull();
    assertThat(returnedPerson.getId()).isEqualTo(1);
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testFindByIdNotFound() throws EntityDoesNotExists {
    when(personRepository.existsById(1)).thenReturn(false);

    personService.findById(1);
    verify(personRepository, never()).findById(1);
  }

  @Test
  public void testSaveCreate() throws EntityDoesNotExists {
    when(organizationService.existsById(1)).thenReturn(true);

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Person person = new Person();
    person.setOrganization(1);
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");

    PersonDto personDto = new PersonDto();
    personDto.setOrganization(organizationDto);
    personDto.setEmail("john.smith@company.com");
    personDto.setLocation(location);
    personDto.setName("John");
    personDto.setSurname("Smith");

    when(personMapper.domainToDto(person)).thenReturn(personDto);

    PersonDto updatedPersonDto = new PersonDto();
    updatedPersonDto.setId(1);
    updatedPersonDto.setOrganization(organizationDto);
    updatedPersonDto.setEmail("john.smith@company.com");
    updatedPersonDto.setLocation(location);
    updatedPersonDto.setName("John");
    updatedPersonDto.setSurname("Smith");

    when(personRepository.save(personDto)).thenReturn(updatedPersonDto);

    Person updatedPerson = new Person();
    updatedPerson.setId(1);
    updatedPerson.setOrganization(1);
    updatedPerson.setEmail("john.smith@company.com");
    updatedPerson.setLocation(location);
    updatedPerson.setName("John");
    updatedPerson.setSurname("Smith");

    when(personMapper.dtoToDomain(updatedPersonDto)).thenReturn(updatedPerson);

    Person returnedPerson = personService.save(person);
    assertThat(returnedPerson).isNotNull();
    assertThat(returnedPerson.getId()).isEqualTo(1);
  }

  @Test
  public void testSaveUpdate() throws EntityDoesNotExists {
    when(organizationService.existsById(1)).thenReturn(true);
    when(personRepository.existsById(1)).thenReturn(true);

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Person person = new Person();
    person.setId(1);
    person.setOrganization(1);
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");

    PersonDto personDto = new PersonDto();
    personDto.setId(1);
    personDto.setOrganization(organizationDto);
    personDto.setEmail("john.smith@company.com");
    personDto.setLocation(location);
    personDto.setName("John");
    personDto.setSurname("Smith");

    when(personMapper.domainToDto(person)).thenReturn(personDto);

    PersonDto updatedPersonDto = new PersonDto();
    updatedPersonDto.setId(1);
    updatedPersonDto.setOrganization(organizationDto);
    updatedPersonDto.setEmail("john.smith@company.com");
    updatedPersonDto.setLocation(location);
    updatedPersonDto.setName("John");
    updatedPersonDto.setSurname("Smith");

    when(personRepository.save(personDto)).thenReturn(updatedPersonDto);

    Person updatedPerson = new Person();
    updatedPerson.setId(1);
    updatedPerson.setOrganization(1);
    updatedPerson.setEmail("john.smith@company.com");
    updatedPerson.setLocation(location);
    updatedPerson.setName("John");
    updatedPerson.setSurname("Smith");

    when(personMapper.dtoToDomain(updatedPersonDto)).thenReturn(updatedPerson);

    Person returnedPerson = personService.save(person);
    assertThat(returnedPerson).isNotNull();
    assertThat(returnedPerson.getId()).isEqualTo(1);
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testSaveUpdateNotFound() throws EntityDoesNotExists {
    when(organizationService.existsById(1)).thenReturn(true);
    when(personRepository.existsById(1)).thenReturn(false);

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Person person = new Person();
    person.setId(1);
    person.setOrganization(1);
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    personService.save(person);
    verify(personRepository, never()).save(any());
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testSaveOrganizationNotFound() throws EntityDoesNotExists {
    when(organizationService.existsById(1)).thenReturn(false);

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Person person = new Person();
    person.setId(1);
    person.setOrganization(1);
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    personService.save(person);
    verify(personRepository, never()).save(any());
  }

}
