package org.ecclesia.directory.repository;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class PersonRepositoryIntegrationTest {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  private Person person;

  @Before
  public void init() {
    Organization organization = new Organization();
    organization.setName("Greenpeace");
    Organization createdOrganization = organizationRepository.save(organization).get();

    person = new Person();
    person.setOrganization(createdOrganization);
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
  }

  @Test
  public void testDeleteById() {
    Person savedPerson = personRepository.save(person).get();
    personRepository.deleteById(savedPerson.getId());

    assertThat(personRepository.existsById(savedPerson.getId())).isFalse();
  }

  @Test
  public void testFindAllByOrganizationId() {
    Organization organization = person.getOrganization();
    personRepository.save(person);

    Location location = new Location();
    location.setAddress1("Shakespeare’s Globe");
    location.setAddress2("21 New Globe Walk");
    location.setCountry("GB");
    location.setPostcode("SE1 9DT");
    location.setTown("London");

    person = new Person();
    person.setEmail("william.shakespeare@theglobe.com");
    person.setLocation(location);
    person.setName("William");
    person.setSurname("Shakespeare");
    person.setOrganization(organization);

    personRepository.save(person);

    List<Person> people = personRepository.findAllByOrganizationId(organization.getId());

    assertThat(people.size()).isEqualTo(2);
  }

  @Test
  public void testFindById() {
    Person savedPerson = personRepository.save(person).get();
    Person loadedPerson = personRepository.findById(savedPerson.getId()).get();
    assertThat(loadedPerson.getId()).isEqualTo(savedPerson.getId());
    assertThat(loadedPerson.getName()).isEqualTo("John");
  }

  @Test
  public void testSave() {
    Person savedPerson = personRepository.save(person).get();

    assertThat(personRepository.existsById(savedPerson.getId())).isTrue();
  }

}
