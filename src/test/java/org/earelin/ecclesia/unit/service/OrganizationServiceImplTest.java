package org.earelin.ecclesia.unit.service;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.repository.OrganizationRepository;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.OrganizationServiceImpl;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

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

    @Test
    public void createNewOrganization() {        
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Organization organization = (Organization) args[0];
                organization.setId(1);
                return null;
            } 
        }).when(repository).add(any(Organization.class));
        
        OrganizationDto organization = new OrganizationDto();
        
        Date beforeInsert = new Date();
        instance.add(organization);
        Date afterInsert = new Date();
        
        assertNotEquals(0, organization.getId());
        assertTrue(organization.getCreated().compareTo(beforeInsert) >= 0
                && organization.getCreated().compareTo(afterInsert) <= 0);
        verify(repository).add(any(Organization.class));
    }
    
    @Test(expected = ValidationException.class)
    public void shouldNotCreateExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setId(1);
        
        instance.add(organization);
    }

    @Test
    public void updateExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setId(1);
        organization.setName("Test organization");
        
        Organization organizationEntity = new Organization();
        organizationEntity.setId(1);
        
        when(repository.get(1)).thenReturn(organizationEntity);
                
        Date beforeUpdate = new Date();
        instance.update(organization); 
        Date afterUpdate = new Date();
        
        assertTrue(organization.getUpdated().compareTo(beforeUpdate) >= 0
                && organization.getUpdated().compareTo(afterUpdate) <= 0);
        assertEquals(organization.getName(), organizationEntity.getName());
        verify(repository).update(any(Organization.class));
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updateANotExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setId(1);
        
        instance.update(organization);
    }

    @Test
    public void removeExistingOrganization() {
        Organization organizationEntity = new Organization();
        
        when(repository.get(1)).thenReturn(organizationEntity);
        
        instance.remove(1);
        
        verify(repository).remove(organizationEntity);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingOrganization() {
        instance.remove(1);
    }

    @Test
    public void getExistingOrganization() {
        Organization organizationEntity = new Organization();
        
        when(repository.get(1)).thenReturn(organizationEntity);
        
        instance.get(1);
        
        verify(repository).get(1);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingOrganization() {
        instance.get(1); 
        verify(repository).get(1);
    }
    
    @Test
    public void checkThanAnOrganizationExists() {
        when(repository.get(1)).thenReturn(new Organization());
        
        instance.exists(1);
        
        verify(repository).get(1);
    }
    
    @Test
    public void checkThanAnOrganizationDoesNoExist() {
        assertFalse(instance.exists(1));
        verify(repository).get(1);
    }
    
}
