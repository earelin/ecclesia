package org.earelin.ecclesia.unit.service.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.earelin.ecclesia.service.resource.FileService;
import org.earelin.ecclesia.service.resource.ImageProcessingServiceImpl;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * ImageProcessingServiceImpl class unit test
 */
public class ImageProcessingServiceImplTest {
    
    public static final String SAMPLE_IMAGE_PATH = "public:///samples/image.png";
    
    ImageProcessingServiceImpl instance;
    
    @Before
    public void initialize() {
        FileService mockedFileService = mock(FileService.class);
        instance = new ImageProcessingServiceImpl(mockedFileService);
    }
    
    @Test
    public void shouldReturnDerivedImagesUri() throws URISyntaxException {
        Map<String, URI> paths = instance.getGeneratedImagesPaths(new URI(SAMPLE_IMAGE_PATH));
        for (String imageKey : paths.keySet()) {
            URI expectedPath = new URI("public://" + ImageProcessingServiceImpl.GENERATED_IMAGES_PATH
                    + "/" + imageKey + "/samples/image.png");
            assertEquals("Expected generated image path does not match", expectedPath, paths.get(imageKey));
        }
    }
    
    @Ignore
    @Test
    public void shouldReturnDerivedImagesUrls() throws URISyntaxException {
        // TODO implement test
    }
    
}
