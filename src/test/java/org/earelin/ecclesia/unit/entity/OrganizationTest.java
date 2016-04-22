package org.earelin.ecclesia.unit.entity;

import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Testing organization domain class
 */
public class OrganizationTest {
    
    @Test
    public void organizationsWithSameIdShouldBeEquals() {
        Organization organization1 = new Organization();
        organization1.setId(1);
        Organization organization2 = new Organization();
        organization2.setId(1);
        assertTrue("Organizations with same id should be equals",
                organization1.equals(organization2));
    }
    
    @Test
    public void organizationsWithDifferentIdShouldNotBeEquals() {
        Organization organization1 = new Organization();
        organization1.setId(1);
        Organization organization2 = new Organization();
        organization2.setId(2);
        assertFalse("Organizations with different id should not be equals",
                organization1.equals(organization2));
    }
    
    @Test
    public void organizationShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        Group group = new Group();
        assertFalse("Organizations should not be equal to another class object",
                organization.equals(group));
    }
    
}
