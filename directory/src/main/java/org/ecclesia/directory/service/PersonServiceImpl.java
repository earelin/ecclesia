package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.repository.PersonRepository;
import org.ecclesia.directory.service.converter.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

  private final PersonMapper personMapper;
  private final PersonRepository personRepository;
  private final OrganizationService organizationService;

  @Autowired
  public PersonServiceImpl(
        PersonMapper personMapper,
        PersonRepository personRepository,
        OrganizationService organizationService) {
    this.personMapper = personMapper;
    this.personRepository = personRepository;
    this.organizationService = organizationService;
  }

  @Override
  public void deleteById(long id) throws EntityDoesNotExists {

  }

  @Override
  public Person findById(long id) throws EntityDoesNotExists {
    return null;
  }

  @Override
  public List<Person> findAllByOrganization(long organizationId) throws EntityDoesNotExists {
    return null;
  }

  @Override
  public Person save(Person person) {
    return null;
  }
}
