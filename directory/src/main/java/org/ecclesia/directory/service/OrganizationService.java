package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Organization;

import java.util.List;

public interface OrganizationService {
  void delete(long id);
  Organization get(long id);
  void save(Organization organization);
  List<Organization> getAll();
}
