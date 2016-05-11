package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.dto.UserDto;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * User data transfer object class test
 */
public class UserDtoTest {
    
    private UserDto user;
    
    @Before
    public void initialize() {
        user = new UserDto();
        user.setId(1);
    }

    @Test
    public void usersShouldBeEqualToItself() {        
        assertTrue("Group object should be equal to itself",
                user.equals(user));
        assertTrue("User object should have the same hash code",
                user.hashCode() == user.hashCode());
    }
    
    @Test
    public void usersWithSameIdShouldBeEquals() {
        UserDto user1 = new UserDto();
        user1.setId(1);
        assertTrue("Groups with same id should be equals",
                user.equals(user1));
        assertTrue("Groups with same id should have the same hash code",
                user.hashCode() == user1.hashCode());
    }
    
    @Test
    public void usersWithDifferentIdShouldNotBeEquals() {
        UserDto user1 = new UserDto();
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
        UserDto user1 = null;
        assertFalse("Groups should not be equal to null",
                user.equals(user1));
    }
    
}
