package org.earelin.ecclesia.integration.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.repository.OrganizationRepository;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Organization Repository integration test
 */
@ContextConfiguration(locations = "classpath:spring-test-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrganizationRepositoryTest {
    
    @Autowired
    private OrganizationRepository repository;        
    
    @Test
    public void shouldAddAnOrganization() {
        Organization organization = new Organization();
        organization.setName("Organization name");
        
        repository.add(organization);
        
        List<Organization> organizations = repository.findAll();
        assertNotEquals(0, organization.getId());
        assertEquals(1, organizations.size());
        assertThat(organization, samePropertyValuesAs(organizations.get(0)));
    }
    
    @Test
    public void shouldUpdateAnOrganization() {
        Organization organization = new Organization();
        organization.setName("Organization name");        
        repository.add(organization);
        
        organization.setName("Updated organization name");        
        repository.update(organization);
        
        List<Organization> organizations = repository.findAll();
        assertEquals(1, organizations.size());
        assertThat(organization, samePropertyValuesAs(organizations.get(0)));
    }
    
    @Test
    public void shouldDeleteAnOrganization() {
        Organization organization = new Organization();
        organization.setName("Organization name");        
        repository.add(organization);
        
        repository.remove(organization);
        
        List<Organization> organizations = repository.findAll();
        assertEquals(0, organizations.size());        
    }
    
    @Test
    public void shouldGetAnOrganization() {
        Organization organization = new Organization();
        organization.setName("Organization name");        
        repository.add(organization);
        
        Organization savedOrganization = repository.get(organization.getId());
        
        assertThat(organization, samePropertyValuesAs(savedOrganization));
    }
    
}
