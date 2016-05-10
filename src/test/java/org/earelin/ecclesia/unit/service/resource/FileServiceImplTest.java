package org.earelin.ecclesia.unit.service.resource;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import org.apache.tika.Tika;
import org.earelin.ecclesia.service.exception.UnhandledFileProtocol;
import org.earelin.ecclesia.service.resource.FileService;
import org.earelin.ecclesia.service.resource.FileServiceImpl;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * FileServiceImpl unit testing
 */
@RunWith(MockitoJUnitRunner.class)
public class FileServiceImplTest {
    
    private static final String PRIVATE_FOLDER = "/tmp/ecclesia_test/private";
    private static final String PUBLIC_FOLDER = "/tmp/ecclesia_test/public";
    private static final String PUBLIC_FILE_SERVER = "http://cdn.example.com";
    private static final String SERVER_URL = "http://www.example.com";
    
    private FileService instance;
    
    @Mock
    private Tika tika;
    
    @Before
    public void init() throws IOException {
        instance = new FileServiceImpl(PRIVATE_FOLDER, PUBLIC_FOLDER,
                PUBLIC_FILE_SERVER, SERVER_URL, tika);
    }
    
    @Test
    public void shouldResolvePrivateUriToUrl() throws Exception {
        URI privateFileUri = new URI("private:///folder/example.txt");
        URL resolvedFileUrl = instance.getUrl(privateFileUri);
        
        assertEquals(SERVER_URL + "/files/folder/example.txt", resolvedFileUrl.toString());
    }
    
    @Test
    public void shouldResolvePublicUriToUrl() throws Exception {
        URI publicFileUri = new URI("public:///folder/example.txt");
        URL resolvedFileUrl = instance.getUrl(publicFileUri);
        
        assertEquals(PUBLIC_FILE_SERVER + "/folder/example.txt", resolvedFileUrl.toString());
    }

    @Test(expected = UnhandledFileProtocol.class)
    public void shouldNotResolveNotHandledProtocolUriGettingUrl() throws Exception {
        instance.getUrl(new URI("illegal:///folder/example.txt"));
    }
    
}
