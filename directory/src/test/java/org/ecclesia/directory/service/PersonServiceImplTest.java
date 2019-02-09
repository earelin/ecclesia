package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.repository.PersonRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class PersonServiceImplTest {

  private PersonServiceImpl personService;

  @Mock
  private PersonRepository personRepository;

  @Mock
  private OrganizationService organizationService;

  @Before
  public void init() {
    personService = new PersonServiceImpl(personRepository, organizationService);
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
    Organization organizationDto = new Organization();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");

    List<Person> people = new ArrayList<>();

    Person person = new Person();
    person.setOrganization(organizationDto);
    person.setName("John");
    person.setSurname("Smith");
    person.setEmail("john.smith@gmail.com");

    Location location = new Location();
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
    person.setOrganization(organizationDto);

    location = new Location();
    location.setAddress1("Shakespeare’s Globe");
    location.setAddress2("21 New Globe Walk");
    location.setCountry("GB");
    location.setPostcode("SE1 9DT");
    location.setTown("London");

    people.add(person);

    when(personRepository.findAllByOrganizationId(1)).thenReturn(people);

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

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
    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    Person personDto = new Person();
    personDto.setId(1);
    personDto.setOrganization(organization);
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

    when(personRepository.findById(1)).thenReturn(Optional.of(personDto));

    Person returnedPerson = personService.findById(1);
    assertThat(returnedPerson.getId()).isEqualTo(1);
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testFindByIdNotFound() throws EntityDoesNotExists {
    personService.findById(1);
    verify(personRepository, never()).findById(1);
  }

  @Test
  public void testSaveCreate() throws EntityDoesNotExists, ErrorSavingEntity {
    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    Person personDto = new Person();
    personDto.setOrganization(organization);
    personDto.setEmail("john.smith@company.com");
    personDto.setLocation(location);
    personDto.setName("John");
    personDto.setSurname("Smith");

    Person updatedPersonDto = new Person();
    updatedPersonDto.setId(1);
    updatedPersonDto.setOrganization(organization);
    updatedPersonDto.setEmail("john.smith@company.com");
    updatedPersonDto.setLocation(location);
    updatedPersonDto.setName("John");
    updatedPersonDto.setSurname("Smith");

    when(personRepository.save(personDto)).thenReturn(Optional.of(updatedPersonDto));

    Person returnedPerson = personService.save(personDto);
    assertThat(returnedPerson.getId()).isEqualTo(1);
  }

  @Test
  public void testSaveUpdate() throws EntityDoesNotExists, ErrorSavingEntity {
    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    Person person = new Person();
    person.setId(1);
    person.setOrganization(organization);
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    Person updatedPersonDto = new Person();
    updatedPersonDto.setId(1);
    updatedPersonDto.setOrganization(organization);
    updatedPersonDto.setEmail("john.smith@company.com");
    updatedPersonDto.setLocation(location);
    updatedPersonDto.setName("John");
    updatedPersonDto.setSurname("Smith");

    when(personRepository.save(person)).thenReturn(Optional.of(updatedPersonDto));

    Person returnedPerson = personService.save(person);
    assertThat(returnedPerson.getId()).isEqualTo(1);
  }

  @Ignore
  @Test(expected = EntityDoesNotExists.class)
  public void testSaveUpdateNotFound() throws EntityDoesNotExists, ErrorSavingEntity {
    when(personRepository.existsById(1)).thenReturn(false);

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Person person = new Person();
    person.setId(1);
    person.setOrganization(organization);
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    personService.save(person);
    verify(personRepository, never()).save(any());
  }

  @Ignore
  @Test(expected = EntityDoesNotExists.class)
  public void testSaveOrganizationNotFound() throws EntityDoesNotExists, ErrorSavingEntity {
    when(personRepository.existsById(1)).thenReturn(true);
    when(organizationService.existsById(1)).thenReturn(false);

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    Location location = new Location();
    location.setAddress1("66");
    location.setAddress2("Avonmore Road");
    location.setCountry("GB");
    location.setPostcode("W14 8RS");
    location.setTown("London");

    Person person = new Person();
    person.setId(1);
    person.setOrganization(organization);
    person.setEmail("john.smith@company.com");
    person.setLocation(location);
    person.setName("John");
    person.setSurname("Smith");

    personService.save(person);
    verify(personRepository, never()).save(any());
  }

}
