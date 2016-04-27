package org.earelin.ecclesia.integration.service;

import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.service.GroupRoleService;
import org.earelin.ecclesia.service.GroupService;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.GroupDto;
import org.earelin.ecclesia.service.dto.GroupRoleDto;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import static org.junit.Assert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * GroupRoleService integration test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class GroupRoleServiceIntegrationTest {
    
    private static final String ORGANIZATION_NAME = "Testing organization";
    private static final String GROUP_NAME = "Testing group";
    private static final String GROUP_ROLE_NAME = "Testing group role";
    
    private GroupDto group;
    
    @Autowired
    private GroupRoleService instance;
    
    @Autowired
    private GroupService groupService;
    
    @Autowired
    private OrganizationService organizationService;
    
    @Before
    public void init() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        organizationService.add(organization);
        
        group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        groupService.add(group);
    }
    
    @Test
    public void createNewGroupRole() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        
        assertNotSame("Created group role id should not be 0", 0, role.getId());
        assertEquals("Group should belong to defined group",
                group, role.getGroup());
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void newGroupRoleShouldHaveNotBlankName() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName("  ");
        instance.add(role);
    }
    
    @Test(expected = ValidationException.class)
    public void newGroupShouldBelongToAnOrganization() {
        GroupRoleDto role = new GroupRoleDto();
        role.setName(GROUP_ROLE_NAME);
        instance.add(role);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void newGroupRoleShouldBelongToAnExistingGroup() {
        GroupDto group = new GroupDto();
        group.setName(GROUP_NAME);
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        instance.add(role);
    }
    
    @Test
    public void updateExistingGroupRole() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        instance.add(role);
        
        long roleId = role.getId();
        String updatedName = "Testing group role updated";
        role.setName(updatedName);
        GroupRoleDto updatedRole = instance.get(roleId);
        
        assertEquals(role.getId(), updatedRole.getId());
        assertEquals(updatedName, role.getName());
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updateNotExistingGroupRole() {
        GroupRoleDto role = new GroupRoleDto();
        role.setId(100000);
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        instance.update(role);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void updatedGroupRoleShouldHaveNotBlankName() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        instance.add(role);    
        role.setName("   ");
        instance.update(role);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedGroupRoleShouldBelongToAGroup() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        instance.add(role);
        
        role.setGroup(null);
        instance.update(role);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updatedGroupRoleShouldBelongToAnExistingGroup() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        instance.add(role);
        
        GroupDto group1 = new GroupDto();
        group1.setName(GROUP_NAME);
        role.setGroup(group1);
        
        instance.update(role);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeExistingGroupRole() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        instance.add(role);      
        long roleId = role.getId();
        
        instance.remove(roleId);
        
        instance.get(roleId);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingGroupRole() {
        instance.remove(100000);
    }
    
    @Test
    public void getExistingGroupRole() {
        GroupRoleDto role = new GroupRoleDto();
        role.setGroup(group);
        role.setName(GROUP_ROLE_NAME);
        instance.add(role);
        
        GroupRoleDto gottenRole = instance.get(role.getId());
        
        assertThat(role, samePropertyValuesAs(gottenRole));
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingGroupRole() {
        instance.get(100000);
    }
    
}
