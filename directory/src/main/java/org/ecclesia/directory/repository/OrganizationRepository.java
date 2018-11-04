package org.ecclesia.directory.repository;

import org.ecclesia.directory.entity.OrganizationDto;
import org.springframework.data.repository.Repository;

public interface OrganizationRepository extends Repository<OrganizationDto, Long> {
  void deleteById(long id);
  boolean existsById(long id);
  OrganizationDto findById(long id);
  OrganizationDto save(OrganizationDto organizationDto);
}
