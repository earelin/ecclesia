package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.service.dto.OrganizationRoleDTO;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing role domain class
 */
public class OrganizationRoleDTOTest {
    
    OrganizationRoleDTO role;
    
    @Before
    public void initialize() {
        role = new OrganizationRoleDTO();
        role.setId(1);
    }
    
    @Test
    public void organizationRolesShouldBeEqualToItself() {
        assertTrue("Organization role object should be equal to itself",
                role.equals(role));
    }
    
    @Test
    public void organizationRolesWithSameIdShouldBeEquals() {
        OrganizationRoleDTO role1 = new OrganizationRoleDTO();
        role1.setId(1);
        assertTrue("Organization roles with same id should be equals",
                role.equals(role1));
    }
    
    @Test
    public void organizationRolesWithDifferentIdShouldNotBeEquals() {
        OrganizationRoleDTO role1 = new OrganizationRoleDTO();
        role1.setId(2);
        assertFalse("Organization roles with different id should not be equals",
                role.equals(role1));
    }
    
    @Test
    public void organizationRolesShouldNotBeEqualToAnotherClass() {
        Group group = new Group();
        group.setId(1);
        assertFalse("Organization roles should not be equal to another class object",
                role.equals(group));
    }
    
    @Test
    public void organizationRolesShouldNotBeEqualToNull() {
        assertFalse("Organization roles should not be equal to null",
                role.equals(null));
    }
    
}
