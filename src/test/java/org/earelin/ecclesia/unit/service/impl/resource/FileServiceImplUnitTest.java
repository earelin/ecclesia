package org.earelin.ecclesia.unit.service.impl.resource;

import java.io.IOException;
import org.apache.tika.Tika;
import org.earelin.ecclesia.service.impl.resource.FileServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * FileServiceImpl unit testing
 */
public class FileServiceImplUnitTest {
    
    FileServiceImpl fileService;
    
    @Before
    public void initialize() throws IOException {
        Tika mockedTika = mock(Tika.class);
        fileService = new FileServiceImpl("/tmp/public", "/tmp/private",
                "http://www.example.com/public", mockedTika);
    }
    
    @Ignore
    @Test
    public void shouldResolvePrivateUriToPathAndUrl() {
        
    }
    
    @Ignore
    @Test
    public void shouldResolvePublicUriToPathAndUrl() {
        
    }
    
    @Ignore
    @Test
    public void shouldNotResolveNotHandledProtocolUri() {
        
    }

}
