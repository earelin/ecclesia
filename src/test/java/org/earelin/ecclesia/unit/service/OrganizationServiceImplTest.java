package org.earelin.ecclesia.unit.service;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.repository.OrganizationRepository;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.OrganizationServiceImpl;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import static org.junit.Assert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * OrganizationServiceImpl unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceImplTest {
        
    private OrganizationService instance;
    private final Mapper mapper = new DozerBeanMapper();
    
    @Mock
    private OrganizationRepository repository;
    
    @Before
    public void init() {
        instance = new OrganizationServiceImpl(repository, mapper);
    }

    @Ignore
    @Test
    public void createNewOrganization() {
        OrganizationDto organization = new OrganizationDto();
        
        Date beforeInsert = new Date();
        instance.add(organization);
        Date afterInsert = new Date();
        
        assertNotSame("Created organization id should not be 0", 0, organization.getId());
        assertTrue("Created organization created field should have current date", 
                organization.getCreated().compareTo(beforeInsert) >= 0
                && organization.getCreated().compareTo(afterInsert) <= 0);
    }

    @Ignore
    @Test
    public void updateExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
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
    
    @Test(expected = EntityNotFoundException.class)
    public void updateANotExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setId(1);
        
        instance.update(organization);
    }

    @Ignore
    @Test(expected = EntityNotFoundException.class)
    public void removeExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        instance.add(organization);      
        long organizationId = organization.getId();
        
        instance.remove(organizationId);
        
        instance.get(organizationId);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingOrganization() {
        instance.remove(1);
    }

    @Ignore
    @Test
    public void getExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        
        OrganizationDto gottenOrganization = instance.get(organization.getId());
        
        assertThat(organization, samePropertyValuesAs(gottenOrganization));
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingOrganization() {
        instance.get(1);        
    }
    
    @Ignore
    @Test
    public void checkThanAnOrganizationExists() {
        
    }
    
    @Test
    public void checkThanAnOrganizationDoesNoExist() {
        assertFalse(instance.exists(1));
        verify(repository).get(1);
    }
    
}
