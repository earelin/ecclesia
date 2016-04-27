package org.earelin.ecclesia.integration.service;

import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.service.OrganizationRoleService;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.dto.OrganizationRoleDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * OrganizationRoleService integration test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class OrganizationRoleServiceIntegrationTest {
    
    private static final String ORGANIZATION_NAME = "Testing organization";
    private static final String ORGANIZATION_ROLE_NAME = "Testing organization role";
    
    private OrganizationDto organization;
    
    @Autowired
    private OrganizationRoleService instance;
    
    @Autowired
    private OrganizationService organizationService;
    
    @Before
    public void init() {
        organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        organizationService.add(organization);
    }
    
    @Test
    public void createNewOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
        
        assertNotSame("Created organization role id should not be 0", 0, role.getId());
        assertEquals("Role organization should belong to defined organization",
                organization, role.getOrganization());
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void newOrganizationRoleShouldHaveNotBlankName() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName("  ");
        instance.add(role);
    }
    
    @Test(expected = ValidationException.class)
    public void newOrganizationRoleShouldBelongToAnOrganization() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setName(ORGANIZATION_ROLE_NAME);
        instance.add(role);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void newOrganizationRoleShouldBelongToAnExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
        instance.add(role);
    }
    
    @Test
    public void updateExistingOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
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
        role.setId(100000);
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
        instance.update(role);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void updatedOrganizationRoleShouldHaveNotBlankName() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
        instance.add(role);    
        role.setName("   ");
        instance.update(role);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedOrganizationRoleShouldBelongToAGroup() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
        instance.add(role);
        
        role.setOrganization(null);
        instance.update(role);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updatedOrganizationRoleShouldBelongToAnExistingGroup() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
        instance.add(role);
        
        OrganizationDto organization1 = new OrganizationDto();
        organization1.setName(ORGANIZATION_NAME);
        role.setOrganization(organization1);
        
        instance.update(role);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeExistingOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
        instance.add(role);      
        long roleId = role.getId();
        
        instance.remove(roleId);
        
        instance.get(roleId);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingOrganizationRole() {
        instance.remove(100000);
    }
    
    @Test
    public void getExistingOrganizationRole() {
        OrganizationRoleDto role = new OrganizationRoleDto();
        role.setOrganization(organization);
        role.setName(ORGANIZATION_ROLE_NAME);
        instance.add(role);
        
        OrganizationRoleDto gottenRole = instance.get(role.getId());
        
        assertThat(role, samePropertyValuesAs(gottenRole));
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingOrganizationRole() {
        instance.get(100000);
    }

}
