package org.earelin.ecclesia.unit.domain;

import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Group class test
 */
public class GroupTest {

    @Test
    public void groupsShouldBeEqualToItself() {
        Group group = new Group();
        group.setId(1);
        assertTrue("Group object should be equal to itself",
                group.equals(group));
    }
    
    @Test
    public void groupsWithSameIdShouldBeEquals() {
        Group group1 = new Group();
        group1.setId(1);
        Group group2 = new Group();
        group2.setId(1);
        assertTrue("Groups with same id should be equals",
                group1.equals(group2));
        assertTrue("Groups with same id should have the same hash code",
                group1.hashCode() == group2.hashCode());
    }
    
    @Test
    public void groupsWithDifferentIdShouldNotBeEquals() {
        Group group1 = new Group();
        group1.setId(1);
        Group group2 = new Group();
        group2.setId(2);
        assertFalse("Groups with different id should not be equals",
                group1.equals(group2));
        assertFalse("Groups with different id should not have the same hash code",
                group1.hashCode() == group2.hashCode());
    }
    
    @Test
    public void groupsShouldNotBeEqualToAnotherClass() {
        Group group = new Group();
        group.setId(1);
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("Groups should not be equal to another class object",
                organization.equals(group));
        assertFalse("Groups should not have the same hash code as another class object",
                organization.hashCode() == group.hashCode());
    }
    
    @Test
    public void groupsShouldNotBeEqualToNull() {
        Group group = new Group();
        group.setId(1);
        assertFalse("Groups should not be equal to null",
                group.equals(null));
    }
    
}
