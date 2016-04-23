package org.earelin.ecclesia.unit.domain;

import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Group class test
 */
public class GroupTest {
    
    Group group;
    
    @Before
    public void initialize() {
        group = new Group();
        group.setId(1);
    }

    @Test
    public void groupsShouldBeEqualToItself() {        
        assertTrue("Group object should be equal to itself",
                group.equals(group));
    }
    
    @Test
    public void groupsWithSameIdShouldBeEquals() {
        Group group1 = new Group();
        group1.setId(1);
        assertTrue("Groups with same id should be equals",
                group.equals(group1));
        assertTrue("Groups with same id should have the same hash code",
                group.hashCode() == group1.hashCode());
    }
    
    @Test
    public void groupsWithDifferentIdShouldNotBeEquals() {
        Group group1 = new Group();
        group1.setId(2);
        assertFalse("Groups with different id should not be equals",
                group.equals(group1));
        assertFalse("Groups with different id should not have the same hash code",
                group.hashCode() == group1.hashCode());
    }
    
    @Test
    public void groupsShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("Groups should not be equal to another class object",
                group.equals(organization));
        assertFalse("Groups should not have the same hash code as another class object",
                organization.hashCode() == group.hashCode());
    }
    
    @Test
    public void groupsShouldNotBeEqualToNull() {
        assertFalse("Groups should not be equal to null",
                group.equals(null));
    }
    
}
