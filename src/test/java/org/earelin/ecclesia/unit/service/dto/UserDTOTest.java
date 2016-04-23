package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.unit.domain.*;
import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.domain.User;
import org.earelin.ecclesia.service.dto.UserDTO;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * User data transfer object class test
 */
public class UserDTOTest {
    
    UserDTO user;
    
    @Before
    public void initialize() {
        user = new UserDTO();
        user.setId(1);
    }

    @Test
    public void usersShouldBeEqualToItself() {        
        assertTrue("Group object should be equal to itself",
                user.equals(user));
    }
    
    @Test
    public void usersWithSameIdShouldBeEquals() {
        UserDTO user1 = new UserDTO();
        user1.setId(1);
        assertTrue("Groups with same id should be equals",
                user.equals(user1));
        assertTrue("Groups with same id should have the same hash code",
                user.hashCode() == user1.hashCode());
    }
    
    @Test
    public void usersWithDifferentIdShouldNotBeEquals() {
        UserDTO user1 = new UserDTO();
        user1.setId(2);
        assertFalse("Groups with different id should not be equals",
                user.equals(user1));
        assertFalse("Groups with different id should not have the same hash code",
                user.hashCode() == user1.hashCode());
    }
    
    @Test
    public void usersShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("Groups should not be equal to another class object",
                user.equals(organization));
        assertFalse("Groups should not have the same hash code as another class object",
                organization.hashCode() == user.hashCode());
    }
    
    @Test
    public void usersShouldNotBeEqualToNull() {
        assertFalse("Groups should not be equal to null",
                user.equals(null));
    }
    
}
