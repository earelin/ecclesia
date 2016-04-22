package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.service.dto.GroupDTO;
import org.earelin.ecclesia.service.dto.OrganizationDTO;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 */
public class GroupDTOTest {
    
    @Test
    public void groupsShouldBeEqualToItself() {
        GroupDTO group = new GroupDTO();
        group.setId(1);
        assertTrue("Group object should be equal to itself",
                group.equals(group));
    }
    
    @Test
    public void groupsWithSameIdShouldBeEquals() {
        GroupDTO group1 = new GroupDTO();
        group1.setId(1);
        GroupDTO group2 = new GroupDTO();
        group2.setId(1);
        assertTrue("Groups with same id should be equals",
                group1.equals(group2));
        assertTrue("Groups with same id should have the same hash code",
                group1.hashCode() == group2.hashCode());
    }
    
    @Test
    public void groupsWithDifferentIdShouldNotBeEquals() {
        GroupDTO group1 = new GroupDTO();
        group1.setId(1);
        GroupDTO group2 = new GroupDTO();
        group2.setId(2);
        assertFalse("Groups with different id should not be equals",
                group1.equals(group2));
        assertFalse("Groups with different id should not have the same hash code",
                group1.hashCode() == group2.hashCode());
    }
    
    @Test
    public void groupsShouldNotBeEqualToAnotherClass() {
        GroupDTO group = new GroupDTO();
        group.setId(1);
        OrganizationDTO organization = new OrganizationDTO();
        organization.setId(1);
        assertFalse("Groups should not be equal to another class object",
                organization.equals(group));
        assertFalse("Groups should not have the same hash code as another class object",
                organization.hashCode() == group.hashCode());
    }
    
    @Test
    public void groupsShouldNotBeEqualToNull() {
        GroupDTO group = new GroupDTO();
        group.setId(1);
        assertFalse("Groups should not be equal to null",
                group.equals(null));
    }
}
