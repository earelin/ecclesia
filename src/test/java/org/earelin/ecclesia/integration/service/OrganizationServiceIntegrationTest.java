package org.earelin.ecclesia.integration.service;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import static org.junit.Assert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * OrganizationService integration test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class OrganizationServiceIntegrationTest {
    
    private static final String ORGANIZATION_NAME = "Testing organization";
    
    @Autowired
    private OrganizationService instance;

    @Test
    public void createNewOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        
        Date beforeInsert = new Date();
        instance.add(organization);
        Date afterInsert = new Date();
        
        assertNotSame("Created organization id should not be 0", 0, organization.getId());
        assertTrue("Created organization created field should have current date", 
                organization.getCreated().compareTo(beforeInsert) >= 0
                && organization.getCreated().compareTo(afterInsert) <= 0);
        assertEquals("Created organization updated field should have the same value as created field", 
                organization.getCreated(), organization.getUpdated());
        assertEquals("Created organization name should be equal to submited", 
                ORGANIZATION_NAME, organization.getName());
    }

    @Test(expected = ConstraintViolationException.class)
    public void newOrganizationShouldHaveNotBlankName() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName("  ");
        instance.add(organization);
    }

    @Test
    public void updateExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        instance.add(organization);
        
        long organizationId = organization.getId();
        String updatedName = "Testing organization updated";
        organization.setName(updatedName);
        Date beforeUpdate = new Date();
        instance.update(organization); 
        Date afterUpdate = new Date();
        organization = instance.get(organizationId);
        
        assertTrue("Updated organization updated field should have current date", 
                organization.getUpdated().compareTo(beforeUpdate) >= 0
                && organization.getUpdated().compareTo(afterUpdate) <= 0);
        assertEquals(updatedName, organization.getName());
    }

    @Test(expected = ConstraintViolationException.class)
    public void updatedOrganizationShouldHaveNotBlankName() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        instance.add(organization);    
        organization.setName("   ");
        instance.update(organization);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updateANoExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setId(100000);
        organization.setName(ORGANIZATION_NAME);
        instance.update(organization);
    }

    @Test(expected = EntityNotFoundException.class)
    public void removeExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        instance.add(organization);      
        long organizationId = organization.getId();
        
        instance.remove(organizationId);
        
        instance.get(organizationId);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNoExistingOrganization() {
        instance.remove(100000);
    }

    @Test
    public void getExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        instance.add(organization);
        
        OrganizationDto gottenOrganization = instance.get(organization.getId());
        
        assertThat(organization, samePropertyValuesAs(gottenOrganization));
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingOrganization() {
        instance.get(100000);        
    }
    
    @Test
    public void checkThanAnOrganizationExists() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        instance.add(organization);
        
        assertTrue(instance.exists(organization.getId()));
    }
    
    @Test
    public void checkThanAnOrganizationDoesNoExist() {
        assertFalse(instance.exists(1000000));
    }
    
}
