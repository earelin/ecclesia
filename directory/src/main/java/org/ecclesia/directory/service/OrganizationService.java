package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Organization;

import java.util.List;

/**
 * Organization objects operations.
 */
public interface OrganizationService {
  void deleteById(long id) throws EntityDoesNotExists;
  Organization findById(long id) throws EntityDoesNotExists;
  List<Organization> findAll();
  Organization save(Organization organization);
}
