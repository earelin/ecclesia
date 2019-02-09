package org.ecclesia.directory.service;

import org.ecclesia.directory.ContentGeneration;
import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.repository.OrganizationRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class OrganizationServiceImplTest {

  @Mock
  private OrganizationRepository organizationRepository;

  private OrganizationServiceImpl organizationService;

  @Before
  public void init() {
    organizationService = new OrganizationServiceImpl(organizationRepository);
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
  public void testExistsById() {
    when(organizationRepository.existsById(1)).thenReturn(true);
    assertThat(organizationService.existsById(1)).isTrue();

    when(organizationRepository.existsById(2)).thenReturn(false);
    assertThat(organizationService.existsById(2)).isFalse();
  }

  @Test
  public void testFindAll() {
    List<Organization> organizationDtos = new ArrayList<>();

    Organization organizationDto = new Organization();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");
    organizationDtos.add(organizationDto);

    organizationDto = new Organization();
    organizationDto.setId(2);
    organizationDto.setName("CeaseFire");
    organizationDtos.add(organizationDto);

    when(organizationRepository.findAll()).thenReturn(organizationDtos);

    List<Organization> returnedOrganizations = organizationService.findAll();
    assertThat(returnedOrganizations.size()).isEqualTo(2);
  }

  @Test
  public void testFindById() throws EntityDoesNotExists {
    Organization organizationDto = ContentGeneration.generateOrganization();
    when(organizationRepository.findById(1)).thenReturn(Optional.of(organizationDto));

    Organization returnedOrganization = organizationService.findById(1);
    assertThat(returnedOrganization.getId()).isEqualTo(1);
    assertThat(returnedOrganization.getName()).isEqualTo("Greenpeace");
  }

  @Ignore
  @Test(expected = EntityDoesNotExists.class)
  public void testFindByIdNotFound() throws EntityDoesNotExists {
    when(organizationRepository.existsById(1)).thenReturn(false);

    organizationService.findById(1);
    verify(organizationRepository, never()).findById(1);
  }

  @Test
  public void testSave() throws EntityDoesNotExists, ErrorSavingEntity {
    Organization organization = new Organization();
    organization.setName("Greenpeace");

    Organization createdOrganizationDto = new Organization();
    createdOrganizationDto.setId(1);
    createdOrganizationDto.setName("Greenpeace");
    when(organizationRepository.save(organization)).thenReturn(Optional.of(createdOrganizationDto));

    Organization testOrganization = organizationService.save(organization);

    assertThat(testOrganization.getId()).isEqualTo(1);
    assertThat(testOrganization.getName()).isEqualTo("Greenpeace");
  }

  @Ignore
  @Test(expected = EntityDoesNotExists.class)
  public void testSaveUpdateNotFound() throws EntityDoesNotExists, ErrorSavingEntity {
    when(organizationRepository.existsById(1)).thenReturn(false);

    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    organizationService.save(organization);
    verify(organizationRepository, never()).save(any());
  }
}
