package org.earelin.ecclesia.unit.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.OrganizationRole;
import org.earelin.ecclesia.repository.OrganizationRoleRepository;
import org.earelin.ecclesia.service.OrganizationRoleService;
import org.earelin.ecclesia.service.OrganizationRoleServiceImpl;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.dto.OrganizationRoleDto;
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
 * OrganizationRoleServiceImpl unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationRoleServiceImplTest {
    
    private OrganizationDto organization;
    private final Mapper mapper = new DozerBeanMapper();
    private OrganizationRoleService instance;
    
    @Mock
    private OrganizationService organizationService;
    
    @Mock
    private OrganizationRoleRepository repository;
    
    @Before
    public void init() {
        organization = new OrganizationDto();
        organization.setId(1);
        
        instance = new OrganizationRoleServiceImpl(repository, organizationService, mapper);
    }
    
    @Test
    public void createNewOrganizationRole() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                OrganizationRole role = (OrganizationRole) args[0];
                role.setId(1);
                return null;
            } 
        }).when(repository).add(any(OrganizationRole.class));
        
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        
        when(organizationService.exists(1)).thenReturn(true);
        
        instance.add(role);
        
        assertNotEquals(0, role.getId());
        verify(repository).add(any(OrganizationRole.class));
    }
    
    @Test(expected = ValidationException.class)
    public void newOrganizationRoleShouldNotExists() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setId(1);
        
        instance.add(role);
    }
    
    @Test(expected = ValidationException.class)
    public void newOrganizationRoleShouldBelongToAnOrganization() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        instance.add(role);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void newOrganizationRoleShouldBelongToAnExistingOrganization() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        instance.add(role);
    }
    
    @Test
    public void updateExistingOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setId(1);
        
        OrganizationRole organizationRole = new OrganizationRole();
        
        when(repository.get(1)).thenReturn(organizationRole);
        when(organizationService.exists(1)).thenReturn(true);
        
        instance.update(role);
        
        verify(repository).update(organizationRole);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updateNotExistingOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setId(1);
        
        instance.update(role);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedOrganizationRoleShouldBelongToAnOrganization() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setId(1);
        
        OrganizationRole organizationRole = new OrganizationRole();
        
        when(repository.get(1)).thenReturn(organizationRole);
        
        instance.update(role);        
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updatedOrganizationRoleShouldBelongToAnExistingOrganization() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setId(1);
        
        OrganizationRole organizationRole = new OrganizationRole();
        
        when(repository.get(1)).thenReturn(organizationRole);
        
        instance.update(role);
    }
    
    @Test
    public void removeExistingOrganizationRole() {
        OrganizationRole roleEntity = new OrganizationRole();
        roleEntity.setId(1);
        
        when(repository.get(1)).thenReturn(roleEntity);
        
        instance.remove(1);
        
        verify(repository).remove(roleEntity);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingOrganizationRole() {
        instance.remove(1);
    }
    
    @Test
    public void getExistingOrganizationRole() {
        OrganizationRole roleEntity = new OrganizationRole();
        roleEntity.setId(1);
        
        when(repository.get(1)).thenReturn(roleEntity);
        
        instance.get(1);
        
        verify(repository).get(1);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingOrganizationRole() {
        instance.get(1);
    }

}
