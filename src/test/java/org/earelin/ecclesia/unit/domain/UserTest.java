package org.earelin.ecclesia.unit.domain;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.domain.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * User class test
 */
public class UserTest {
    
    User user;
    
    @Before
    public void initialize() {
        user = new User();
        user.setId(1);
    }

    @Test
    public void usersShouldBeEqualToItself() {        
        assertTrue("User object should be equal to itself",
                user.equals(user));
        assertTrue("User object should have the same hash code",
                user.hashCode() == user.hashCode());
    }
    
    @Test
    public void usersWithSameIdShouldBeEquals() {
        User user1 = new User();
        user1.setId(1);
        assertTrue("Users with same id should be equals",
                user.equals(user1));
        assertTrue("Users with same id should have the same hash code",
                user.hashCode() == user1.hashCode());
    }
    
    @Test
    public void usersWithDifferentIdShouldNotBeEquals() {
        User user1 = new User();
        user1.setId(2);
        assertFalse("Users with different id should not be equals",
                user.equals(user1));
        assertFalse("Users with different id should not have the same hash code",
                user.hashCode() == user1.hashCode());
    }
    
    @Test
    public void usersShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("Users should not be equal to another class object",
                user.equals(organization));
        assertFalse("Users should not have the same hash code as another class object",
                organization.hashCode() == user.hashCode());
    }
    
    @Test
    public void usersShouldNotBeEqualToNull() {
        assertFalse("Users should not be equal to null",
                user.equals(null));
    }
    
}
