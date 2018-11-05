package org.ecclesia.directory.service;

import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.entity.OrganizationDto;
import org.ecclesia.directory.repository.OrganizationRepository;
import org.ecclesia.directory.service.converter.OrganizationMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class OrganizationServiceImplTest {

  @Mock
  private OrganizationRepository organizationRepository;

  @Mock
  private OrganizationMapper organizationMapper;

  private OrganizationServiceImpl organizationService;

  @Before
  public void init() {
    organizationService = new OrganizationServiceImpl(organizationRepository, organizationMapper);
  }

  @Test
  public void testDeleteById() throws EntityDoesNotExists {
    when(organizationRepository.existsById(1)).thenReturn(true);

    organizationService.deleteById(1);
    verify(organizationRepository).deleteById(1);
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testDeleteByIdNotFound() throws EntityDoesNotExists {
    when(organizationRepository.existsById(1)).thenReturn(false);

    organizationService.deleteById(1);
    verify(organizationRepository, never()).deleteById(1);
  }

  @Test
  public void testFindAll() {
    List<OrganizationDto> organizationDtos = new ArrayList<>();

    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");
    organizationDtos.add(organizationDto);

    organizationDto = new OrganizationDto();
    organizationDto.setId(2);
    organizationDto.setName("CeaseFire");
    organizationDtos.add(organizationDto);

    when(organizationRepository.findAll()).thenReturn(organizationDtos);

    List<Organization> organizations = new ArrayList<>();

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");
    organizations.add(organization);

    organization = new Organization();
    organization.setId(2);
    organization.setName("Médecins Sans Frontières");
    organizations.add(organization);

    when(organizationMapper.dtoListToDomainList(organizationDtos)).thenReturn(organizations);

    List<Organization> returnedOrganizations = organizationService.findAll();
    assertThat(returnedOrganizations.size()).isEqualTo(2);
  }

  @Test
  public void testFindById() throws EntityDoesNotExists {
    when(organizationRepository.existsById(1)).thenReturn(true);

    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");
    when(organizationRepository.findById(1)).thenReturn(organizationDto);

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");
    when(organizationMapper.dtoToDomain(organizationDto)).thenReturn(organization);

    Organization returnedOrganization = organizationService.findById(1);
    assertThat(returnedOrganization.getId()).isEqualTo(1);
    assertThat(returnedOrganization.getName()).isEqualTo("Greenpeace");
  }

  @Test(expected = EntityDoesNotExists.class)
  public void testFindByIdNotFound() throws EntityDoesNotExists {
    when(organizationRepository.existsById(1)).thenReturn(false);

    organizationService.findById(1);
    verify(organizationRepository, never()).findById(1);
  }

  @Test
  public void testSave() {
    Organization organization = new Organization();
    organization.setName("Greenpeace");

    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setName("Greenpeace");
    when(organizationMapper.domainToDto(organization)).thenReturn(organizationDto);

    OrganizationDto createdOrganizationDto = new OrganizationDto();
    createdOrganizationDto.setId(1);
    createdOrganizationDto.setName("Greenpeace");
    when(organizationRepository.save(organizationDto)).thenReturn(createdOrganizationDto);

    Organization createdOrganization = new Organization();
    createdOrganization.setId(1);
    createdOrganization.setName("Greenpeace");
    when(organizationMapper.dtoToDomain(createdOrganizationDto)).thenReturn(createdOrganization);

    Organization testOrganization = organizationService.save(organization);
    assertThat(testOrganization.getId()).isEqualTo(1);
    assertThat(testOrganization.getName()).isEqualTo("Greenpeace");
  }

}
