package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.entity.PersonDto;
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
    if (!personRepository.existsById(id)) {
      throw new EntityDoesNotExists(String.format("Person with id %d does not exists", id));
    }

    personRepository.deleteById(id);
  }

  @Override
  public Person findById(long id) throws EntityDoesNotExists {
    if (!personRepository.existsById(id)) {
      throw new EntityDoesNotExists(String.format("Person with id %d does not exists", id));
    }

    PersonDto personDto = personRepository.findById(id);
    return personMapper.dtoToDomain(personDto);
  }

  @Override
  public List<Person> findAllByOrganization(long organizationId) throws EntityDoesNotExists {
    if (!organizationService.existsById(organizationId)) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists", organizationId));
    }
    List<PersonDto> peopleDtos = personRepository.findAllByOrganizationId(1);
    return personMapper.dtoListToDomainList(peopleDtos);
  }

  @Override
  public Person save(Person person) throws EntityDoesNotExists {
    if (!organizationService.existsById(person.getOrganization())) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists", person.getOrganization()));
    }

    if (!person.isNew() && !personRepository.existsById(person.getId())) {
      throw new EntityDoesNotExists(String.format("Person with id %d does not exists, cannot be updated", person.getId()));
    }
    PersonDto personDto = personMapper.domainToDto(person);
    return personMapper.dtoToDomain(personRepository.save(personDto));
  }
}
