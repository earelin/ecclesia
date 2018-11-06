package org.ecclesia.directory.repository;

import org.ecclesia.directory.entity.OrganizationDto;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends Repository<OrganizationDto, Long> {
  void deleteById(long id);
  boolean existsById(long id);
  List<OrganizationDto> findAll();
  Optional<OrganizationDto> findById(long id);
  Optional<OrganizationDto> save(OrganizationDto organizationDto);
}
