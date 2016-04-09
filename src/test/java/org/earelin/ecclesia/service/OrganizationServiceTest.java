package org.earelin.ecclesia.service;

import java.util.Date;
import org.earelin.ecclesia.domain.Organization;
import static org.junit.Assert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import org.earelin.ecclesia.service.exception.OrganizationNotFoundException;
import org.junit.Before;
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
    
    @Before
    public void init() {
    }

    /**
     * Test of add method.
     */
    @Test
    public void createNewOrganization() {
        Organization organization = new Organization(ORGANIZATION_NAME);
        
        Date beforeInsert = new Date();
        Organization createdOrganization = instance.add(organization);
        Date afterInsert = new Date();
        
        assertNotSame("Created organization id should not be 0", 0, createdOrganization.getId());
        assertTrue("Created organization created field should have current date", 
                createdOrganization.getCreated().after(beforeInsert) 
                    || createdOrganization.getCreated().equals(beforeInsert)
                && createdOrganization.getCreated().before(afterInsert)
                    || createdOrganization.getCreated().equals(afterInsert));
        assertEquals("Created organization updated field should have the same value as created field", 
                createdOrganization.getCreated(), createdOrganization.getUpdated());
        assertEquals("Created organization name not equals to submited", 
                ORGANIZATION_NAME, createdOrganization.getName());
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
        Organization organization = instance.add(new Organization(ORGANIZATION_NAME));
        
        instance.remove(organization.getId());
        
        instance.get(organization.getId());
    }

    /**
     * Test of get method.
     */
    @Test
    public void getExistingOrganization() {
        Organization organization = instance.add(new Organization(ORGANIZATION_NAME));
        
        Organization gottenOrganization = instance.get(organization.getId());
        assertThat(gottenOrganization, samePropertyValuesAs(gottenOrganization));
    }
    
}
