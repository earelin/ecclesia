package org.earelin.ecclesia.unit.service.resource;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import org.earelin.ecclesia.service.resource.FileService;
import org.earelin.ecclesia.service.resource.ImageProcessingServiceImpl;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * ImageProcessingServiceImpl class unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageProcessingServiceImplTest {
    
    private ImageProcessingServiceImpl instance;
    
    @Mock
    private FileService fileService;
    
    @Before
    public void initialize() {
        instance = new ImageProcessingServiceImpl(fileService);
    }
    
    @Test
    public void shouldReturnDerivedImagesUri() throws URISyntaxException {
        Map<String, URI> paths = instance.getGeneratedImagesPaths(new URI("public:///samples/image.png"));
        for (Map.Entry<String, URI> image : paths.entrySet()) {
            URI expectedPath = new URI("public://" + ImageProcessingServiceImpl.GENERATED_IMAGES_PATH
                    + "/" + image.getKey() + "/samples/image.png");
            assertEquals(expectedPath, image.getValue());
        }
    }
    
    @Test
    public void shouldReturnDerivedImagesUrls() throws URISyntaxException, Exception {
        doAnswer((Answer) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            URI uri = (URI) args[0];
            return new URL("http://cdn.example.com" + uri.getPath()); 
        }).when(fileService).getUrl(any(URI.class));
        
        Map<String, URL> urls = instance.getGeneratedImagesUrls(new URI("public:///samples/image.png"));
        for (Map.Entry<String, URL> image : urls.entrySet()) {
            URL expectedURL = new URL("http://cdn.example.com" + ImageProcessingServiceImpl.GENERATED_IMAGES_PATH
                    + "/" + image.getKey() + "/samples/image.png");
            assertEquals(expectedURL, image.getValue());
        }        
    }        

    @Test
    public void shouldDeleteGenerateStyledImages() throws Exception {                
        instance.deleteGeneratedImages(new URI("public:///samples/image.png"));
        
        Map<String, URI> styledImages = instance.getGeneratedImagesPaths(new URI("public:///samples/image.png"));
        for (URI uri : styledImages.values()) {
            verify(fileService, atLeastOnce()).delete(uri);
        }
    }        
    
}
