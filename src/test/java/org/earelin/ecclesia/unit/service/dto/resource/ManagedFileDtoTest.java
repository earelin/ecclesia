package org.earelin.ecclesia.unit.service.dto.resource;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Managed file data transfer object class test
 */
public class ManagedFileDtoTest {
    
    ManagedFileDto file;
    
    @Before
    public void initialize() {
        file = new ManagedFileDto();
        file.setId(1);
    }

    @Test
    public void managedFilesShouldBeEqualToItself() {        
        assertTrue("Managed file object should be equal to itself",
                file.equals(file));
        assertTrue("Managed file object should have the same hash code as itself",
                file.hashCode() == file.hashCode());
    }
    
    @Test
    public void managedFilesWithSameIdShouldBeEquals() {
        ManagedFileDto file1 = new ManagedFileDto();
        file1.setId(1);
        assertTrue("Managed files with same id should be equals",
                file.equals(file1));
        assertTrue("Managed files with same id should have the same hash code",
                file.hashCode() == file1.hashCode());
    }
    
    @Test
    public void managedFilesWithDifferentIdShouldNotBeEquals() {
        ManagedFileDto file1 = new ManagedFileDto();
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
