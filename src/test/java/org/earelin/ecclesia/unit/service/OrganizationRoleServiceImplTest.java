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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

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
    
    @Ignore
    @Test
    public void createNewOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        
        assertNotSame("Created organization role id should not be 0", 0, role.getId());
        assertEquals("Role organization should belong to defined organization",
                organization, role.getOrganization());
    }
    
    @Test(expected = ValidationException.class)
    public void newOrganizationRoleShouldBelongToAnOrganization() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        instance.add(role);
    }
    
    @Ignore
    @Test(expected = EntityNotFoundException.class)
    public void newOrganizationRoleShouldBelongToAnExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        instance.add(role);
    }
    
    @Ignore
    @Test
    public void updateExistingOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        instance.add(role);
        
        long roleId = role.getId();
        String updatedName = "Testing organization role updated";
        role.setName(updatedName);
        OrganizationRoleDto updatedRole = instance.get(roleId);
        
        assertEquals(role.getId(), updatedRole.getId());
        assertEquals(updatedName, role.getName());
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updateNotExistingOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setId(1);
        
        instance.update(role);
    }
    
    @Ignore
    @Test(expected = ValidationException.class)
    public void updatedOrganizationRoleShouldBelongToAnOrganization() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        instance.add(role);
        
        role.setOrganization(null);
        instance.update(role);
    }
    
    @Ignore
    @Test(expected = EntityNotFoundException.class)
    public void updatedOrganizationRoleShouldBelongToAnExistingOrganization() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        instance.add(role);
        
        OrganizationDto organization1 = new OrganizationDto();
        role.setOrganization(organization1);
        
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
