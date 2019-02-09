package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

  private final OrganizationRepository organizationRepository;

  @Autowired
  public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }

  @Override
  public void deleteById(long id) throws EntityDoesNotExists {
    if (!organizationRepository.existsById(id)) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists", id));
    }

    organizationRepository.deleteById(id);
  }

  @Override
  public boolean existsById(long id) {
    return organizationRepository.existsById(id);
  }

  @Override
  public Organization findById(long id) throws EntityDoesNotExists {
    Optional<Organization> organization = organizationRepository.findById(id);

    if (!organization.isPresent()) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists", id));
    }

    return organization.get();
  }

  @Override
  public List<Organization> findAll() {
    return organizationRepository.findAll();
  }

  @Override
  public Organization save(Organization organization) throws EntityDoesNotExists, ErrorSavingEntity {
    Optional<Organization> savedOrganization = organizationRepository.save(organization);
    if (!savedOrganization.isPresent()) {
      throw new ErrorSavingEntity("Error saving organization");
    }

    return savedOrganization.get();
  }

}
