package org.ecclesia.directory.repository;

import org.ecclesia.directory.entity.PersonDto;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersonRepository extends Repository<PersonDto, Long> {
  void deleteById(long id);
  boolean existsById(long id);
  List<PersonDto> findAllByOrganizationId(long organizationId);
  PersonDto findById(long id);
  PersonDto save(PersonDto personDto);
}
