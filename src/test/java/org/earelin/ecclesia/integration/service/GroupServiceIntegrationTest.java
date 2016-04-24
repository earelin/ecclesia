package org.earelin.ecclesia.integration.service;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.service.GroupService;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.GroupDto;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.exception.GroupNotFoundException;
import org.earelin.ecclesia.service.exception.OrganizationNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import static org.junit.Assert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * GroupServiceImpl unit test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class GroupServiceIntegrationTest {
    
    private static final String GROUP_NAME = "Testing group";
    private static final String ORGANIZATION_NAME = "Testing groups organization";
    
    private OrganizationDto organization;
    
    @Autowired
    private GroupService instance;
    
    @Autowired
    private OrganizationService organizationService;
    
    @Before
    public void init() {
        organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        organizationService.add(organization);
    }
    
    @Test
    public void createNewGroup() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);

        Date beforeInsert = new Date();
        instance.add(group);
        Date afterInsert = new Date();
        
        assertNotSame("Created group id should not be 0", 0, group.getId());
        assertTrue("Created group created field should have current date", 
                group.getCreated().compareTo(beforeInsert) >= 0
                && group.getCreated().compareTo(afterInsert) <= 0);
        assertEquals("Created group updated field should have the same value as created field", 
                group.getCreated(), group.getUpdated());
        assertEquals("Group should belong to defined Organization",
                organization, group.getOrganization());
    }
    
    @Test
    public void createNewGroupWithParent() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        
        GroupDto parent = new GroupDto();
        parent.setOrganization(organization);
        parent.setName("Parent group name");
        instance.add(parent);
        
        group.setParent(parent);
        instance.add(group);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void newGroupShouldHaveNotBlankName() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName("  ");
        instance.add(group);
    }
    
    @Test(expected = ValidationException.class)
    public void newGroupShouldBelongToAnOrganization() {
        GroupDto group = new GroupDto();
        group.setName(GROUP_NAME);
        instance.add(group);
    }
    
    @Test(expected = OrganizationNotFoundException.class)
    public void newGroupShouldBelongToAnExistingOrganization() {
        OrganizationDto organization = new OrganizationDto();
        organization.setName(ORGANIZATION_NAME);
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
    }
    
    @Test(expected = ValidationException.class)
    public void newGroupParentShouldExists() {
        GroupDto parent = new GroupDto();
        parent.setOrganization(organization);
        parent.setName("Parent group name");
        
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        group.setParent(parent);
        
        instance.add(group);
    }
    
    @Test(expected = ValidationException.class)
    public void newGroupParentShouldBelongToTheSameOrganization() {
        OrganizationDto organization1 = new OrganizationDto();
        organization1.setName(ORGANIZATION_NAME);
        organizationService.add(organization1);
        
        GroupDto parent = new GroupDto();
        parent.setOrganization(organization1);
        parent.setName("Parent group name");
        instance.add(parent);
        
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        group.setParent(parent);
        
        instance.add(group);
    }
    
    @Test
    public void updateExistingGroup() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        long groupId = group.getId();
        String updatedName = "Testing group updated";
        group.setName(updatedName);
        Date beforeUpdate = new Date();
        instance.update(group); 
        Date afterUpdate = new Date();
        GroupDto updatedGroup = instance.get(groupId);
        
        assertTrue("Updated group updated field should have current date", 
                updatedGroup.getUpdated().compareTo(beforeUpdate) >= 0
                && updatedGroup.getUpdated().compareTo(afterUpdate) <= 0);
        assertEquals(group.getId(), updatedGroup.getId());
        assertEquals(updatedName, group.getName());
    }
    
    @Test
    public void updateExistingGroupWithParent() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        GroupDto parent = new GroupDto();
        parent.setOrganization(organization);
        parent.setName("Parent group name");
        instance.add(parent);
        
        group.setParent(parent);
        instance.update(group);
    }
    
    @Test(expected = GroupNotFoundException.class)
    public void updateNotExistingGroup() {
        GroupDto group = new GroupDto();
        group.setId(100000);
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.update(group);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void updatedGroupShouldHaveNotBlankName() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);    
        group.setName("   ");
        instance.update(group);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedGroupShouldBelongToAnOrganization() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);    
        group.setOrganization(null);
        instance.update(group);
    }
    
    @Test(expected = OrganizationNotFoundException.class)
    public void updatedGroupShouldBelongToAnExistingOrganization() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        OrganizationDto organization1 = new OrganizationDto();
        organization1.setName(ORGANIZATION_NAME);
        group.setOrganization(organization1);
        
        instance.update(group);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedGroupParentShouldExists() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        GroupDto parent = new GroupDto();
        parent.setOrganization(organization);
        parent.setName("Parent group name");
        
        group.setParent(parent);
        
        instance.update(group);
    }
    
    @Test(expected = ValidationException.class)
    public void updatedGroupParentShouldBelongToTheSameOrganization() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        OrganizationDto organization1 = new OrganizationDto();
        organization1.setName(ORGANIZATION_NAME);
        organizationService.add(organization1);
        
        GroupDto parent = new GroupDto();
        parent.setOrganization(organization1);
        parent.setName("Parent group name");
        instance.add(parent);
        
        group.setParent(parent);
        
        instance.update(group);
    }
    
    @Test(expected = GroupNotFoundException.class)
    public void removeExistingGroup() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);      
        long groupId = group.getId();
        
        instance.remove(groupId);
        
        instance.get(groupId);
    }
    
    @Test(expected = GroupNotFoundException.class)
    public void removeNotExistingGroup() {
        instance.remove(100000);
    }
    
    @Test
    public void getExistingGroup() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        GroupDto gottenGroup = instance.get(group.getId());
        
        assertThat(group, samePropertyValuesAs(gottenGroup));
    }
    
    @Test
    public void checkThanAGroupExists() {
        GroupDto group = new GroupDto();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        assertTrue(instance.exists(group.getId()));
    }
    
    @Test
    public void checkThanAGroupDoesNoExist() {
        assertFalse(instance.exists(1000000));
    }
    
}
