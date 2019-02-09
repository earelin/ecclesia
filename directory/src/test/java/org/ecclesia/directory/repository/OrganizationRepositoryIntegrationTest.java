package org.ecclesia.directory.repository;

import org.ecclesia.directory.domain.Organization;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationRepositoryIntegrationTest {

  @Autowired
  private OrganizationRepository organizationRepository;

  private Organization organization;

  @Before
  public void init() {
    organization = new Organization();
    organization.setName("Greenpeace");
  }

  @Test
  public void testDeleteById() {
    Organization createdOrganization = organizationRepository.save(organization).get();

    organizationRepository.deleteById(createdOrganization.getId());
    assertThat(organizationRepository.existsById(createdOrganization.getId())).isFalse();
  }

  @Test
  public void testFindAll() {
    organizationRepository.save(organization);

    organization = new Organization();
    organization.setName("Médecins Sans Frontières");
    organizationRepository.save(organization);

    organization = new Organization();
    organization.setName("CeaseFire");
    organizationRepository.save(organization);

    List<Organization> organizations = organizationRepository.findAll();
    assertThat(organizations.size()).isEqualTo(3);
  }

  @Test
  public void testFindById() {
    Organization createdOrganization = organizationRepository.save(organization).get();
    Organization loadedOrganization = organizationRepository.findById(createdOrganization.getId()).get();

    assertThat(loadedOrganization.getId()).isEqualTo(createdOrganization.getId());
    assertThat(loadedOrganization.getName()).isEqualTo("Greenpeace");
  }

  @Test
  public void testSave() {
    Organization createdOrganization = organizationRepository.save(organization).get();

    assertThat(organizationRepository.existsById(createdOrganization.getId())).isTrue();
  }

}
