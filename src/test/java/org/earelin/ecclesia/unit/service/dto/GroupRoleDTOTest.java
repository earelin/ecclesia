package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.dto.GroupRoleDTO;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Group class test
 */
public class GroupRoleDTOTest {
    
    GroupRoleDTO role;
    
    @Before
    public void initialize() {
        role = new GroupRoleDTO();
        role.setId(1);
    }

    @Test
    public void groupRolesShouldBeEqualToItself() {
        assertTrue("Group role object should be equal to itself",
                role.equals(role));
    }
    
    @Test
    public void groupRolesWithSameIdShouldBeEquals() {
        GroupRoleDTO role1 = new GroupRoleDTO();
        role1.setId(1);
        assertTrue("Group roles with same id should be equals",
                role.equals(role1));
        assertTrue("Group roles with same id should have the same hash code",
                role.hashCode() == role1.hashCode());
    }
    
    @Test
    public void groupRolesWithDifferentIdShouldNotBeEquals() {
        GroupRoleDTO role1 = new GroupRoleDTO();
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
