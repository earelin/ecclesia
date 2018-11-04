package org.ecclesia.directory.service.converter;

import org.ecclesia.directory.domain.Organization;
import org.ecclesia.directory.entity.OrganizationDto;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class OrganizationMapperTest {

  private OrganizationMapper organizationMapper;

  @Before
  public void init() {
    organizationMapper = new OrganizationMapperImpl();
  }

  @Test
  public void testDomainToDto() {
    Organization organization = new Organization();
    organization.setId(1);
    organization.setName("Greenpeace");

    OrganizationDto organizationDto = organizationMapper.domainToDto(organization);

    assertThat(organizationDto.getId()).isEqualTo(1);
    assertThat(organizationDto.getName()).isEqualTo("Greenpeace");

    organizationDto = organizationMapper.domainToDto(null);

    assertThat(organizationDto).isNull();
  }

  @Test
  public void testDtoToDomain() {
    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setId(1);
    organizationDto.setName("Greenpeace");

    Organization organization = organizationMapper.dtoToDomain(organizationDto);

    assertThat(organization.getId()).isEqualTo(1);
    assertThat(organization.getName()).isEqualTo("Greenpeace");

    organization = organizationMapper.dtoToDomain(null);

    assertThat(organization).isNull();
  }

}
