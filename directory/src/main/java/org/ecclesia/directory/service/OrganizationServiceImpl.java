package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.entity.OrganizationDto;
import org.ecclesia.directory.repository.OrganizationRepository;
import org.ecclesia.directory.service.converter.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

  private final OrganizationRepository organizationRepository;
  private final OrganizationMapper organizationMapper;

  @Autowired
  public OrganizationServiceImpl(
        OrganizationRepository organizationRepository,
        OrganizationMapper organizationMapper) {
    this.organizationRepository = organizationRepository;
    this.organizationMapper = organizationMapper;
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
  public Optional<Organization> findById(long id) throws EntityDoesNotExists {
    if (!organizationRepository.existsById(id)) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists", id));
    }

    Optional<OrganizationDto> organizationDto = organizationRepository.findById(id);
    if (!organizationDto.isPresent()) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists", id));
    }

    Organization organization = organizationMapper.dtoToDomain(organizationDto.get());
    return Optional.ofNullable(organization);
  }

  @Override
  public List<Organization> findAll() {
    List<OrganizationDto> organizationDtos = organizationRepository.findAll();
    return organizationMapper.dtoListToDomainList(organizationDtos);
  }

  @Override
  public Optional<Organization> save(Organization organization) throws EntityDoesNotExists, ErrorSavingEntity {
    if (!organization.isNew() && !organizationRepository.existsById(organization.getId())) {
      throw new EntityDoesNotExists(String.format("Organization with id %d does not exists, cannot be updated", organization.getId()));
    }
    OrganizationDto organizationDto = organizationMapper.domainToDto(organization);

    Optional<OrganizationDto> savedOrganizationDto = organizationRepository.save(organizationDto);
    if (!savedOrganizationDto.isPresent()) {
      throw new ErrorSavingEntity("Error saving organization");
    }

    Organization savedOrganization = organizationMapper.dtoToDomain(savedOrganizationDto.get());
    return Optional.ofNullable(savedOrganization);
  }

}
