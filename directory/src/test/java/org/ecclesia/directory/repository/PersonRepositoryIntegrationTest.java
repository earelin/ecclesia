package org.ecclesia.directory.repository;

import org.ecclesia.directory.domain.Location;
import org.ecclesia.directory.entity.OrganizationDto;
import org.ecclesia.directory.entity.PersonDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryIntegrationTest {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private OrganizationRepository organizationRepository;

  private PersonDto personDto;

  @Before
  public void init() {
    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setName("Greenpeace");
    OrganizationDto createdOrganizationDto = organizationRepository.save(organizationDto).get();

    personDto = new PersonDto();
    personDto.setOrganization(createdOrganizationDto);
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
  }

  @Test
  public void testDeleteById() {
    PersonDto savedPersonDto = personRepository.save(personDto).get();
    personRepository.deleteById(savedPersonDto.getId());

    assertThat(personRepository.existsById(savedPersonDto.getId())).isFalse();
  }

  @Test
  public void testFindAllByOrganizationId() {
    OrganizationDto organizationDto = personDto.getOrganization();
    personRepository.save(personDto);

    Location location = new Location();
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
    personDto.setOrganization(organizationDto);

    personRepository.save(personDto);

    List<PersonDto> personDtos = personRepository.findAllByOrganizationId(organizationDto.getId());

    assertThat(personDtos.size()).isEqualTo(2);
  }

  @Test
  public void testFindById() {
    PersonDto savedPersonDto = personRepository.save(personDto).get();
    PersonDto loadedPersonDto = personRepository.findById(savedPersonDto.getId()).get();
    assertThat(loadedPersonDto.getId()).isEqualTo(savedPersonDto.getId());
    assertThat(loadedPersonDto.getName()).isEqualTo("John");
  }

  @Test
  public void testSave() {
    PersonDto savedPersonDto = personRepository.save(personDto).get();

    assertThat(personRepository.existsById(savedPersonDto.getId())).isTrue();
  }

}
