package org.earelin.ecclesia.unit.domain;

import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing organization domain class
 */
public class OrganizationTest {
    
    Organization organization;
    
    @Before
    public void initialize() {
        organization = new Organization();
        organization.setId(1);
    }
    
    @Test
    public void organizationsShouldBeEqualToItself() {
        assertTrue("Organization object should be equal to itself",
                organization.equals(organization));
    }
    
    @Test
    public void organizationsWithSameIdShouldBeEquals() {
        Organization organization1 = new Organization();
        organization1.setId(1);
        assertTrue("Organizations with same id should be equals",
                organization.equals(organization1));
    }
    
    @Test
    public void organizationsWithDifferentIdShouldNotBeEquals() {
        Organization organization1 = new Organization();
        organization1.setId(2);
        assertFalse("Organizations with different id should not be equals",
                organization.equals(organization1));
    }
    
    @Test
    public void organizationsShouldNotBeEqualToAnotherClass() {
        Group group = new Group();
        group.setId(1);
        assertFalse("Organizations should not be equal to another class object",
                organization.equals(group));
    }
    
    @Test
    public void organizationsShouldNotBeEqualToNull() {
        assertFalse("Organizations should not be equal to null",
                organization.equals(null));
    }
    
}
