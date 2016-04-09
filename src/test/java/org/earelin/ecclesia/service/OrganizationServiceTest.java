package org.earelin.ecclesia.service;

import java.util.Date;
import org.earelin.ecclesia.entity.Organization;
import org.earelin.ecclesia.web.dto.OrganizationDTO;
import static org.junit.Assert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import org.earelin.ecclesia.service.exception.OrganizationNotFoundException;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * OrganizationServiceImpl unit test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-test-config.xml"})
public class OrganizationServiceTest {
    
    private static final String ORGANIZATION_NAME = "Testing organization";
    
    @Autowired
    private OrganizationService instance;

    /**
     * Test of add method.
     */
    @Test
    public void createNewOrganization() {
        Organization organization = new Organization();
        organization.setName(ORGANIZATION_NAME);
        
        Date beforeInsert = new Date();
        instance.add(organization);
        Date afterInsert = new Date();
        
        assertNotSame("Created organization id should not be 0", 0, organization.getId());
        assertTrue("Created organization created field should have current date", 
                organization.getCreated().after(beforeInsert) 
                    || organization.getCreated().equals(beforeInsert)
                && organization.getCreated().before(afterInsert)
                    || organization.getCreated().equals(afterInsert));
        assertEquals("Created organization updated field should have the same value as created field", 
                organization.getCreated(), organization.getUpdated());
        assertEquals("Created organization name not equals to submited", 
                ORGANIZATION_NAME, organization.getName());
    }

    /**
     * Test of update method.
     */
    @Ignore
    @Test
    public void updateExistingOrganization() {
        
    }

    /**
     * Test of remove method.
     */
    @Test(expected = OrganizationNotFoundException.class)
    public void removeExistingOrganization() {
        Organization organization = new Organization(ORGANIZATION_NAME);
        instance.add(organization);        
        long organizationId = organization.getId();
        
        instance.remove(organization);
        
        instance.get(organizationId);
    }

    /**
     * Test of get method.
     */
    @Test
    public void getExistingOrganization() {
        Organization organization = new Organization(ORGANIZATION_NAME);
        instance.add(organization);
        
        Organization gottenOrganization = instance.get(organization.getId());
        assertThat(organization, samePropertyValuesAs(gottenOrganization));
    }
    
}
