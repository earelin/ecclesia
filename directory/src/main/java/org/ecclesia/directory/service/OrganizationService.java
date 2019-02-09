package org.ecclesia.directory.service;


import java.util.List;
import org.ecclesia.directory.domain.Organization;

/**
 * Organization objects operations.
 */
public interface OrganizationService {
  void deleteById(long id) throws EntityDoesNotExists;
  boolean existsById(long id);
  Organization findById(long id) throws EntityDoesNotExists;
  List<Organization> findAll();
  Organization save(Organization organization) throws EntityDoesNotExists, ErrorSavingEntity;
}
