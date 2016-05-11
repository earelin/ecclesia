package org.earelin.ecclesia.unit.service.dto.resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.dto.resource.ManagedImageDto;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Managed file data transfer object class test
 */
public class ManagedImageDtoTest {
    
    private ManagedImageDto image;
    
    @Before
    public void initialize() throws MalformedURLException {
        Map<String, URL> styles = new HashMap();
        styles.put("style1", new URL("http://cdn.example.com/style1/image.png"));
        styles.put("style2", new URL("http://cdn.example.com/style2/image.png"));
        image = new ManagedImageDto(1, "image/png",
                new Date(), new URL("http://cdn.example.com/image.png"), styles);
    }

    @Test
    public void managedFilesShouldBeEqualToItself() {        
        assertTrue(image.equals(image));
        assertTrue(image.hashCode() == image.hashCode());
    }
    
    @Test
    public void managedFilesWithSameIdShouldBeEquals() throws MalformedURLException {
        Map<String, URL> styles = new HashMap();
        styles.put("style1", new URL("http://cdn.example.com/style1/image.png"));
        styles.put("style2", new URL("http://cdn.example.com/style2/image.png"));
        
        ManagedImageDto image1 = new ManagedImageDto(1, "image/png",
                new Date(), new URL("http://localhost/ecclesia/images/image.png"), styles);
        assertTrue(image.equals(image1));
        assertTrue(image.hashCode() == image1.hashCode());
    }
    
    @Test
    public void managedFilesWithDifferentIdShouldNotBeEquals() throws MalformedURLException {
        Map<String, URL> styles = new HashMap();
        styles.put("style1", new URL("http://cdn.example.com/style1/image.png"));
        styles.put("style2", new URL("http://cdn.example.com/style2/image.png"));
        
        ManagedImageDto image1 = new ManagedImageDto(2, "image/png",
                new Date(), new URL("http://localhost/ecclesia/images/image.png"), styles);
        assertFalse("Managed files with different id should not be equals",
                image.equals(image1));
        assertFalse("Managed files with different id should not have the same hash code",
                image.hashCode() == image1.hashCode());
    }
    
    @Test
    public void managedFilesShouldNotBeEqualToAnotherClass() {
        Organization organization = new Organization();
        organization.setId(1);
        assertFalse("Managed files should not be equal to another class object",
                image.equals(organization));
        assertFalse("Managed files should not have the same hash code as another class object",
                organization.hashCode() == image.hashCode());
    }
    
    @Test
    public void managedFilesShouldNotBeEqualToNull() {
        ManagedImageDto image1 = null;
        assertFalse("Managed files should not be equal to null",
                image.equals(image1));
    }
    
}
