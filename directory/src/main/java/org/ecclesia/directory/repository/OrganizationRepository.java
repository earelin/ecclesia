package org.ecclesia.directory.repository;

import org.ecclesia.directory.domain.Organization;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends Repository<Organization, Long> {
  void deleteById(long id);
  boolean existsById(long id);
  List<Organization> findAll();
  Optional<Organization> findById(long id);
  Optional<Organization> save(Organization organization);
}
