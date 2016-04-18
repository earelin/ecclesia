package org.earelin.ecclesia.integration.service;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.service.GroupService;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.GroupDTO;
import org.earelin.ecclesia.service.dto.OrganizationDTO;
import org.earelin.ecclesia.service.exception.GroupNotFoundException;
import static org.junit.Assert.*;
import static org.hamcrest.beans.SamePropertyValuesAs.*;
import org.junit.Before;
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
    
    private OrganizationDTO organization;
    
    @Autowired
    private GroupService instance;
    
    @Autowired
    private OrganizationService organizationService;
    
    @Before
    public void init() {
        organization = new OrganizationDTO();
        organization.setName(ORGANIZATION_NAME);
        organizationService.add(organization);
    }
    
    @Test
    public void createNewGroup() {
        GroupDTO group = new GroupDTO();
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
    
    @Test(expected = ConstraintViolationException.class)
    public void newGroupShouldHaveNotBlankName() {
        GroupDTO group = new GroupDTO();
        group.setOrganization(organization);
        group.setName("  ");
        instance.add(group);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void newGroupShouldBelongToAnOrganization() {
        GroupDTO group = new GroupDTO();
        group.setName(GROUP_NAME);
        instance.add(group);
    }
    
    @Test
    public void updateExistingGroup() {
        GroupDTO group = new GroupDTO();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        long groupId = group.getId();
        String updatedName = "Testing group updated";
        group.setName(updatedName);
        Date beforeUpdate = new Date();
        instance.update(group); 
        Date afterUpdate = new Date();
        group = instance.get(groupId);
        
        assertTrue("Updated group updated field should have current date", 
                group.getUpdated().compareTo(beforeUpdate) >= 0
                && group.getUpdated().compareTo(afterUpdate) <= 0);
        assertEquals(updatedName, group.getName());
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void updatedGroupShouldHaveNotBlankName() {
        GroupDTO group = new GroupDTO();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);    
        group.setName("   ");
        instance.update(group);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void updatedGroupShouldBelongToAnOrganization() {
        GroupDTO group = new GroupDTO();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);    
        group.setOrganization(null);
        instance.update(group);
    }
    
    @Test(expected = GroupNotFoundException.class)
    public void removeExistingGroup() {
        GroupDTO group = new GroupDTO();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);      
        long groupId = group.getId();
        
        instance.remove(groupId);
        
        instance.get(groupId);
    }
    
    @Test
    public void getExistingGroup() {
        GroupDTO group = new GroupDTO();
        group.setOrganization(organization);
        group.setName(GROUP_NAME);
        instance.add(group);
        
        GroupDTO gottenGroup = instance.get(group.getId());
        
        assertThat(group, samePropertyValuesAs(gottenGroup));
    }
    
}
