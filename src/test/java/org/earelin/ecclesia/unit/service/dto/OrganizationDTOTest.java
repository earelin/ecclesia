package org.earelin.ecclesia.unit.service.dto;

import org.earelin.ecclesia.service.dto.GroupDTO;
import org.earelin.ecclesia.service.dto.OrganizationDTO;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 */
public class OrganizationDTOTest {
    @Test
    public void organizationsWithSameIdShouldBeEquals() {
        OrganizationDTO organization1 = new OrganizationDTO();
        organization1.setId(1);
        OrganizationDTO organization2 = new OrganizationDTO();
        organization2.setId(1);
        assertTrue("Organizations with same id should be equals",
                organization1.equals(organization2));
    }
    
    @Test
    public void organizationsWithDifferentIdShouldNotBeEquals() {
        OrganizationDTO organization1 = new OrganizationDTO();
        organization1.setId(1);
        OrganizationDTO organization2 = new OrganizationDTO();
        organization2.setId(2);
        assertFalse("Organizations with different id should not be equals",
                organization1.equals(organization2));
    }
    
    @Test
    public void organizationShouldNotBeEqualToAnotherClass() {
        OrganizationDTO organization = new OrganizationDTO();
        organization.setId(1);
        GroupDTO group = new GroupDTO();
        assertFalse("Organizations with different id should not be equals",
                organization.equals(group));
    }
}
