package org.ecclesia.directory.repository;

import org.ecclesia.directory.domain.Person;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Repository<Person, Long> {
  void deleteById(long id);
  boolean existsById(long id);
  List<Person> findAllByOrganizationId(long organizationId);
  Optional<Person> findById(long id);
  Optional<Person> save(Person person);
}
