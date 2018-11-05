package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Person;

import java.util.List;

/**
 * Person objects operations.
 */
public interface PersonService {
  void deleteById(long id) throws EntityDoesNotExists;
  Person findById(long id) throws EntityDoesNotExists;
  List<Person> findAllByOrganization(long organizationId) throws EntityDoesNotExists;
  Person save(Person person);
}
