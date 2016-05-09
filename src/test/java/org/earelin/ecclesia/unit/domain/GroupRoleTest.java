package org.earelin.ecclesia.unit.domain;

import org.earelin.ecclesia.domain.GroupRole;
import org.earelin.ecclesia.domain.Organization;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Group class test
 */
public class GroupRoleTest {
    
    private GroupRole role;
    
    @Before
    public void initialize() {
        role = new GroupRole();
        role.setId(1);
    }

    @Test
    public void groupRolesShouldBeEqualToItself() {
        assertTrue("Group role object should be equal to itself",
                role.equals(role));
        assertTrue("Group role should have the same hash code than itself",
                role.hashCode() == role.hashCode());
    }
    
    @Test
    public void groupRolesWithSameIdShouldBeEquals() {
        GroupRole role1 = new GroupRole();
        role1.setId(1);
        assertTrue("Group roles with same id should be equals",
                role.equals(role1));
        assertTrue("Group roles with same id should have the same hash code",
                role.hashCode() == role1.hashCode());
    }
    
    @Test
    public void groupRolesWithDifferentIdShouldNotBeEquals() {
        GroupRole role1 = new GroupRole();
        role1.setId(2);
        assertFalse("Group roles with different id should not be equals",
                role.equals(role1));
        assertFalse("Group roles with different id should not have the same hash code",
                role.hashCode() == role1.hashCode());
    }
    
    @Test
    public void groupRolesShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("Group roles should not be equal to another class object",
                role.equals(organization));
        assertFalse("Group roles should not have the same hash code as another class object",
                organization.hashCode() == role.hashCode());
    }
    
    @Test
    public void groupRolesShouldNotBeEqualToNull() {
        assertFalse("Group roles should not be equal to null",
                role.equals(null));
    }
    
}
