package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Person;
import org.ecclesia.directory.entity.PersonDto;
import org.ecclesia.directory.repository.PersonRepository;
import org.ecclesia.directory.service.converter.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
  public Optional<Person> findById(long id) throws EntityDoesNotExists {
    if (!personRepository.existsById(id)) {
      throw new EntityDoesNotExists(String.format("Person with id %d does not exists", id));
    }

    Optional<PersonDto> personDto = personRepository.findById(id);
    if (!personDto.isPresent()) {
      throw new EntityDoesNotExists(String.format("Person with id %d does not exists", id));
    }

    Person person = personMapper.dtoToDomain(personDto.get());
    return Optional.ofNullable(person);
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
  public Optional<Person> save(Person person) throws EntityDoesNotExists, ErrorSavingEntity {
    if (!person.isNew() && !personRepository.existsById(person.getId())) {
      throw new EntityDoesNotExists(String.format("Person with id %d does not exists, cannot be updated", person.getId()));
    }

    if (!organizationService.existsById(person.getOrganization())) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists", person.getOrganization()));
    }
    PersonDto personDto = personMapper.domainToDto(person);

    Optional<PersonDto> savedPersonDto = personRepository.save(personDto);
    if (!savedPersonDto.isPresent()) {
      throw new ErrorSavingEntity("Error saving person");
    }

    Person savedPerson = personMapper.dtoToDomain(savedPersonDto.get());
    return Optional.ofNullable(savedPerson);
  }
}
