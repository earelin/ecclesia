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
    public void shouldGenerateStyledImages() throws Exception {
//        File file = new File(TEST_IMAGE);
//        URI publicPath = fileService.save(file, new URI("public:///derived/images"));
//        
//        instance.processImage(publicPath);
//        
//        Map<String, URI> styledImages = instance.getGeneratedImagesPaths(publicPath);
//        for (String style : styledImages.keySet()) {
            // TODO implement tests
//            String generatedImagePath = fileService.getPath(styledImages.get(style));
//            assertEquals("Styled image should exists", true,
//                    Files.exists(Paths.get(generatedImagePath)));
//            assertEquals("Styled image should be regular files", true,
//                    Files.isRegularFile(Paths.get(generatedImagePath)));
//        }
    }

    @Ignore
    @Test
    public void shouldDeleteGenerateStyledImages() throws Exception {
//        File file = new File(TEST_IMAGE);
//        URI publicPath = fileService.save(file, new URI("public:///derived/delete"));
//        
//        instance.processImage(publicPath);
//        instance.deleteGeneratedImages(publicPath);
        
//        Map<String, String> styledImages = instance.getGeneratedImagesPaths(publicPath);
//        for (String style : styledImages.keySet()) {
//            String generatedImagePath = fileService.getPath(styledImages.get(style));
//            assertFalse("Generated image should be deleted", Files.exists(Paths.get(generatedImagePath)));
//        }
    }
    
    @Ignore
    @Test
    public void shouldReturnDerivedImagesUrls() throws URISyntaxException {
        // TODO implement test
    }
    
}
