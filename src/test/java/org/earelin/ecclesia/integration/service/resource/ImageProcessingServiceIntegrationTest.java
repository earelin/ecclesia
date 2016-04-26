package org.earelin.ecclesia.integration.service.resource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import org.earelin.ecclesia.service.resource.FileService;
import org.earelin.ecclesia.service.resource.ImageProcessingService;
import static org.junit.Assert.*;
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
    
    @Test
    public void shouldGenerateStyledImages() throws Exception {
        File file = new File(TEST_IMAGE);
        String publicPath = fileService.save(file, "public:///derived/images");
        
        instance.processImage(publicPath);
        
        Map<String, String> styledImages = instance.getGeneratedImagesPaths(publicPath);
        for (String style : styledImages.keySet()) {
            String generatedImagePath = fileService.getPath(styledImages.get(style));
            assertTrue("Styled image should be generated", Files.exists(Paths.get(generatedImagePath)));
        }
    }
    
}
