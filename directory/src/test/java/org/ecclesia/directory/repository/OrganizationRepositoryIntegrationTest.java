package org.ecclesia.directory.repository;

import org.ecclesia.directory.entity.OrganizationDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationRepositoryIntegrationTest {

  @Autowired
  private OrganizationRepository organizationRepository;

  private OrganizationDto organizationDto;

  @Before
  public void init() {
    organizationDto = new OrganizationDto();
    organizationDto.setName("Greenpeace");
  }

  @Test
  public void testDeleteById() {
    OrganizationDto createdOrganization = organizationRepository.save(organizationDto);

    organizationRepository.deleteById(createdOrganization.getId());
    assertThat(organizationRepository.existsById(createdOrganization.getId())).isFalse();
  }

  @Test
  public void testExistsById() {
    OrganizationDto createdOrganization = organizationRepository.save(organizationDto);

    assertThat(organizationRepository.existsById(createdOrganization.getId())).isTrue();
  }

  @Test
  public void testFindById() {
    OrganizationDto createdOrganization = organizationRepository.save(organizationDto);
    OrganizationDto loadedOrganization = organizationRepository.findById(createdOrganization.getId());

    assertThat(loadedOrganization.getId()).isEqualTo(createdOrganization.getId());
    assertThat(loadedOrganization.getName()).isEqualTo("Greenpeace");
  }

}
