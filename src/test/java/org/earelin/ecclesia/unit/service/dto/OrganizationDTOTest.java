package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.service.dto.GroupDTO;
import org.earelin.ecclesia.service.dto.OrganizationDTO;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class OrganizationDTOTest {
    
    OrganizationDTO organization;
    
    @Before
    public void initialize() {
        organization = new OrganizationDTO();
        organization.setId(1);
    }
    
    @Test
    public void organizationShouldBeEqualToItself() {
        assertTrue("Organization object should be equal to itself",
                organization.equals(organization));
    }
    
    @Test
    public void organizationsWithSameIdShouldBeEquals() {
        OrganizationDTO organization1 = new OrganizationDTO();
        organization1.setId(1);
        assertTrue("Organizations with same id should be equals",
                organization.equals(organization1));
        assertTrue("Organizations with same id should have the same hash code",
                organization.hashCode() == organization1.hashCode());
    }
    
    @Test
    public void organizationsWithDifferentIdShouldNotBeEquals() {
        OrganizationDTO organization1 = new OrganizationDTO();
        organization1.setId(2);
        assertFalse("Organizations with different id should not be equals",
                organization.equals(organization1));
        assertFalse("Organizations with different id should not have the same hash code",
                organization.hashCode() == organization1.hashCode());
    }
    
    @Test
    public void organizationShouldNotBeEqualToAnotherClass() {
        GroupDTO group = new GroupDTO();
        group.setId(1);
        assertFalse("Organizations should not be equal to another class object",
                organization.equals(group));
        assertFalse("Organizations should not have the same hash code as another class object",
                organization.hashCode() == group.hashCode());
    }
    
    @Test
    public void organizationShouldNotBeEqualToNull() {
        assertFalse("Organization should not be equal to null",
                organization.equals(null));
    }
    
}
