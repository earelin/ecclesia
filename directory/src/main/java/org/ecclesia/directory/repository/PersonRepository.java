package org.ecclesia.directory.repository;

import org.ecclesia.directory.entity.PersonDto;
import org.springframework.data.repository.Repository;

public interface PersonRepository extends Repository<PersonDto, Long> {
  void deleteById(long id);
  boolean existsById(long id);
  PersonDto findById(long id);
  PersonDto save(PersonDto personDto);
}
