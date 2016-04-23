package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.dto.GroupRoleDTO;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Group class test
 */
public class GroupRoleDTOTest {

    @Test
    public void groupRolesShouldBeEqualToItself() {
        GroupRoleDTO role = new GroupRoleDTO();
        role.setId(1);
        assertTrue("Group role object should be equal to itself",
                role.equals(role));
    }
    
    @Test
    public void groupRolesWithSameIdShouldBeEquals() {
        GroupRoleDTO role1 = new GroupRoleDTO();
        role1.setId(1);
        GroupRoleDTO role2 = new GroupRoleDTO();
        role2.setId(1);
        assertTrue("Group roles with same id should be equals",
                role1.equals(role2));
        assertTrue("Group roles with same id should have the same hash code",
                role1.hashCode() == role2.hashCode());
    }
    
    @Test
    public void groupRolesWithDifferentIdShouldNotBeEquals() {
        GroupRoleDTO role1 = new GroupRoleDTO();
        role1.setId(1);
        GroupRoleDTO role2 = new GroupRoleDTO();
        role2.setId(2);
        assertFalse("Group roles with different id should not be equals",
                role1.equals(role2));
        assertFalse("Group roles with different id should not have the same hash code",
                role1.hashCode() == role2.hashCode());
    }
    
    @Test
    public void groupRolesShouldNotBeEqualToAnotherClass() {
        GroupRoleDTO role = new GroupRoleDTO();
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
        GroupRoleDTO role = new GroupRoleDTO();
        role.setId(1);
        assertFalse("Group roles should not be equal to null",
                role.equals(null));
    }
    
}
