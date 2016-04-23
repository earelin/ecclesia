package org.earelin.ecclesia.unit.domain.resource;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.domain.resource.ManagedFile;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Managed file class test
 */
public class ManagedFileTest {
    
    ManagedFile file;
    
    @Before
    public void initialize() {
        file = new ManagedFile();
        file.setId(1);
    }

    @Test
    public void managedFilesShouldBeEqualToItself() {        
        assertTrue("Managed file object should be equal to itself",
                file.equals(file));
    }
    
    @Test
    public void managedFilesWithSameIdShouldBeEquals() {
        ManagedFile file1 = new ManagedFile();
        file1.setId(1);
        assertTrue("Managed files with same id should be equals",
                file.equals(file1));
        assertTrue("Managed files with same id should have the same hash code",
                file.hashCode() == file1.hashCode());
    }
    
    @Test
    public void managedFilesWithDifferentIdShouldNotBeEquals() {
        ManagedFile file1 = new ManagedFile();
        file1.setId(2);
        assertFalse("Managed files with different id should not be equals",
                file.equals(file1));
        assertFalse("Managed files with different id should not have the same hash code",
                file.hashCode() == file1.hashCode());
    }
    
    @Test
    public void managedFilesShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("Managed files should not be equal to another class object",
                file.equals(organization));
        assertFalse("Managed files should not have the same hash code as another class object",
                organization.hashCode() == file.hashCode());
    }
    
    @Test
    public void managedFilesShouldNotBeEqualToNull() {
        assertFalse("Managed files should not be equal to null",
                file.equals(null));
    }
    
}