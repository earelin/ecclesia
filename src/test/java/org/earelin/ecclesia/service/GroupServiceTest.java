package org.earelin.ecclesia.service;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.entity.Group;
import org.earelin.ecclesia.entity.Organization;
import org.earelin.ecclesia.service.exception.GroupNotFoundException;
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
public class GroupServiceTest {
    
    private static final String GROUP_NAME = "Testing group";
    private static final String ORGANIZATION_NAME = "Testing groups organization";
    
    private Organization organization;
    
    @Autowired
    private GroupService instance;
    
    @Autowired
    private OrganizationService organizationService;
    
    @Before
    public void init() {
        organization = new Organization(ORGANIZATION_NAME);
        organizationService.add(organization);
    }
    
    @Test
    public void createNewGroup() {
        Group group = new Group(organization, GROUP_NAME);

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
        instance.add(new Group(organization, "  "));
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void newGroupShouldBelongToAnOrganization() {
        Group group = new Group();
        group.setName(GROUP_NAME);
        instance.add(group);
    }
    
    @Test
    public void updateExistingGroup() {
        Group group = new Group(organization, GROUP_NAME);
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
        Group group = new Group(organization, GROUP_NAME);
        instance.add(group);    
        group.setName("   ");
        instance.update(group);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void updatedGroupShouldBelongToAnOrganization() {
        Group group = new Group(organization, GROUP_NAME);
        instance.add(group);    
        group.setOrganization(null);
        instance.update(group);
    }
    
    @Test(expected = GroupNotFoundException.class)
    public void removeExistingGroup() {
        Group group = new Group(organization, GROUP_NAME);
        instance.add(group);      
        long groupId = group.getId();
        
        instance.remove(groupId);
        
        instance.get(groupId);
    }
    
    @Test
    public void getExistingGroup() {
        Group group = new Group(organization, GROUP_NAME);
        instance.add(group);
        
        Group gottenGroup = instance.get(group.getId());
        
        assertThat(group, samePropertyValuesAs(gottenGroup));
    }
    
}
