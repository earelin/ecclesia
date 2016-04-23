package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.dto.UserGroupDTO;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * User class test
 */
public class UserGroupDTOTest {
    
    UserGroupDTO userGroup;
    
    @Before
    public void initialize() {
        userGroup = new UserGroupDTO();
        userGroup.setId(1);
    }

    @Test
    public void userGroupsShouldBeEqualToItself() {        
        assertTrue("User group object should be equal to itself",
                userGroup.equals(userGroup));
    }
    
    @Test
    public void userGroupsWithSameIdShouldBeEquals() {
        UserGroupDTO userGroup1 = new UserGroupDTO();
        userGroup1.setId(1);
        assertTrue("User groups with same id should be equals",
                userGroup.equals(userGroup1));
        assertTrue("User groups with same id should have the same hash code",
                userGroup.hashCode() == userGroup1.hashCode());
    }
    
    @Test
    public void userGroupsWithDifferentIdShouldNotBeEquals() {
        UserGroupDTO userGroup1 = new UserGroupDTO();
        userGroup1.setId(2);
        assertFalse("User groups with different id should not be equals",
                userGroup.equals(userGroup1));
        assertFalse("User groups with different id should not have the same hash code",
                userGroup.hashCode() == userGroup1.hashCode());
    }
    
    @Test
    public void userGroupsShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("User groups should not be equal to another class object",
                userGroup.equals(organization));
        assertFalse("User groups should not have the same hash code as another class object",
                organization.hashCode() == userGroup.hashCode());
    }
    
    @Test
    public void userGroupsShouldNotBeEqualToNull() {
        assertFalse("User groups should not be equal to null",
                userGroup.equals(null));
    }
    
}
