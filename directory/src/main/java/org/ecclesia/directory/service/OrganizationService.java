package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Organization;

import java.util.List;
import java.util.Optional;

/**
 * Organization objects operations.
 */
public interface OrganizationService {
  void deleteById(long id) throws EntityDoesNotExists;
  boolean existsById(long id);
  Optional<Organization> findById(long id) throws EntityDoesNotExists;
  List<Organization> findAll();
  Optional<Organization> save(Organization organization) throws EntityDoesNotExists, ErrorSavingEntity;
}
