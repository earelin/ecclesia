package org.earelin.ecclesia.unit.domain;

import org.earelin.ecclesia.domain.GroupRole;
import org.earelin.ecclesia.domain.Organization;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Group class test
 */
public class GroupRoleTest {

    @Test
    public void groupRolesShouldBeEqualToItself() {
        GroupRole role = new GroupRole();
        role.setId(1);
        assertTrue("Group role object should be equal to itself",
                role.equals(role));
    }
    
    @Test
    public void groupRolesWithSameIdShouldBeEquals() {
        GroupRole role1 = new GroupRole();
        role1.setId(1);
        GroupRole role2 = new GroupRole();
        role2.setId(1);
        assertTrue("Group roles with same id should be equals",
                role1.equals(role2));
        assertTrue("Group roles with same id should have the same hash code",
                role1.hashCode() == role2.hashCode());
    }
    
    @Test
    public void groupRolesWithDifferentIdShouldNotBeEquals() {
        GroupRole role1 = new GroupRole();
        role1.setId(1);
        GroupRole role2 = new GroupRole();
        role2.setId(2);
        assertFalse("Group roles with different id should not be equals",
                role1.equals(role2));
        assertFalse("Group roles with different id should not have the same hash code",
                role1.hashCode() == role2.hashCode());
    }
    
    @Test
    public void groupRolesShouldNotBeEqualToAnotherClass() {
        GroupRole role = new GroupRole();
        role.setId(1);
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("Group roles should not be equal to another class object",
                organization.equals(role));
        assertFalse("Group roles should not have the same hash code as another class object",
                organization.hashCode() == role.hashCode());
    }
    
    @Test
    public void groupRolesShouldNotBeEqualToNull() {
        GroupRole role = new GroupRole();
        role.setId(1);
        assertFalse("Group roles should not be equal to null",
                role.equals(null));
    }
    
}
