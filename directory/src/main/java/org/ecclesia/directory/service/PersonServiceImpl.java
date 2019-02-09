package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;
  private final OrganizationService organizationService;

  @Autowired
  public PersonServiceImpl(
        PersonRepository personRepository,
        OrganizationService organizationService) {
    this.personRepository = personRepository;
    this.organizationService = organizationService;
  }

  @Override
  public void deleteById(long id) throws EntityDoesNotExists {
    if (!personRepository.existsById(id)) {
      throw new EntityDoesNotExists(String.format("Person with id %d does not exists", id));
    }

    personRepository.deleteById(id);
  }

  @Override
  public Person findById(long id) throws EntityDoesNotExists {
    Optional<Person> person = personRepository.findById(id);
    if (!person.isPresent()) {
      throw new EntityDoesNotExists(String.format("Person with id %d does not exists", id));
    }

    return person.get();
  }

  @Override
  public List<Person> findAllByOrganization(long organizationId) throws EntityDoesNotExists {
    if (!organizationService.existsById(organizationId)) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists", organizationId));
    }

    return personRepository.findAllByOrganizationId(organizationId);
  }

  @Override
  public Person save(Person person) throws EntityDoesNotExists, ErrorSavingEntity {
    Optional<Person> savedPerson = personRepository.save(person);
    if (!savedPerson.isPresent()) {
      throw new ErrorSavingEntity("Error saving person");
    }

    return savedPerson.get();
  }
}
