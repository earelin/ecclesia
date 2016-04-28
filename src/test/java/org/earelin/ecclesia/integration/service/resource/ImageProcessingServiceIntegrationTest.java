package org.earelin.ecclesia.integration.service.resource;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import org.earelin.ecclesia.service.resource.FileService;
import org.earelin.ecclesia.service.resource.ImageProcessingService;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ImageProcessingService integration test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class ImageProcessingServiceIntegrationTest {
    
    public static final String TEST_IMAGE = "src/test/resources/test-data/images/1000x500.png";
    
    @Autowired
    private ImageProcessingService instance;
    
    @Autowired
    private FileService fileService;
    
    @Ignore
    @Test
    public void shouldGenerateStyledImages() throws Exception {
        File file = new File(TEST_IMAGE);
        URI publicPath = fileService.save(file, new URI("public:///derived/images"));
        
        instance.processImage(publicPath);
        
        Map<String, URI> styledImages = instance.getGeneratedImagesPaths(publicPath);
        for (String style : styledImages.keySet()) {
            // TODO implement tests
//            String generatedImagePath = fileService.getPath(styledImages.get(style));
//            assertEquals("Styled image should exists", true,
//                    Files.exists(Paths.get(generatedImagePath)));
//            assertEquals("Styled image should be regular files", true,
//                    Files.isRegularFile(Paths.get(generatedImagePath)));
        }
    }

    @Ignore
    @Test
    public void shouldDeleteGenerateStyledImages() throws Exception {
        File file = new File(TEST_IMAGE);
        URI publicPath = fileService.save(file, new URI("public:///derived/delete"));
        
        instance.processImage(publicPath);
        instance.deleteGeneratedImages(publicPath);
        
//        Map<String, String> styledImages = instance.getGeneratedImagesPaths(publicPath);
//        for (String style : styledImages.keySet()) {
//            String generatedImagePath = fileService.getPath(styledImages.get(style));
//            assertFalse("Generated image should be deleted", Files.exists(Paths.get(generatedImagePath)));
//        }
    }
    
}
