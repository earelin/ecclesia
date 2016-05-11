package org.earelin.ecclesia.unit.domain;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.domain.UserOrganization;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * User class test
 */
public class UserOrganizationTest {
    
    private UserOrganization userOrganization;
    
    @Before
    public void initialize() {
        userOrganization = new UserOrganization();
        userOrganization.setId(1);
    }

    @Test
    public void userOrganizationsShouldBeEqualToItself() {        
        assertTrue("User organization object should be equal to itself",
                userOrganization.equals(userOrganization));
        assertTrue("User organization object should have the same hash code",
                userOrganization.hashCode() == userOrganization.hashCode());
    }
    
    @Test
    public void userOrganizationsWithSameIdShouldBeEquals() {
        UserOrganization userOrganization1 = new UserOrganization();
        userOrganization1.setId(1);
        assertTrue("User organizations with same id should be equals",
                userOrganization.equals(userOrganization1));
        assertTrue("User organizations with same id should have the same hash code",
                userOrganization.hashCode() == userOrganization1.hashCode());
    }
    
    @Test
    public void userOrganizationsWithDifferentIdShouldNotBeEquals() {
        UserOrganization userOrganization1 = new UserOrganization();
        userOrganization1.setId(2);
        assertFalse("User organizations with different id should not be equals",
                userOrganization.equals(userOrganization1));
        assertFalse("User organizations with different id should not have the same hash code",
                userOrganization.hashCode() == userOrganization1.hashCode());
    }
    
    @Test
    public void userOrganizationsShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("User organizations should not be equal to another class object",
                userOrganization.equals(organization));
        assertFalse("User organizations should not have the same hash code as another class object",
                organization.hashCode() == userOrganization.hashCode());
    }
    
    @Test
    public void userOrganizationsShouldNotBeEqualToNull() {
        UserOrganization userOrganization1 = null;
        assertFalse("User organizations should not be equal to null",
                userOrganization.equals(userOrganization1));
    }
    
}
