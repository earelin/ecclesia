package org.earelin.ecclesia.unit.service.resource;

import java.net.URISyntaxException;
import java.util.Map;
import org.earelin.ecclesia.service.resource.FileService;
import org.earelin.ecclesia.service.resource.ImageProcessingServiceImpl;
import static org.junit.Assert.*;
import org.junit.Before;
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
        Map<String, String> paths = instance.getGeneratedImagesPaths(SAMPLE_IMAGE_PATH);
        for (String imageKey : paths.keySet()) {
            String expectedPath = "public://" + ImageProcessingServiceImpl.GENERATED_IMAGES_PATH
                    + "/" + imageKey + "/samples/image.png";
            assertEquals("Expected generated image path does not match", expectedPath, paths.get(imageKey));
        }
    }
    
}
