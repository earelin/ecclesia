package org.earelin.ecclesia.integration.service.resource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.earelin.ecclesia.service.resource.FileService;
import org.earelin.ecclesia.service.resource.FileServiceImpl;
import org.earelin.ecclesia.service.resource.ImageProcessingService;
import org.earelin.ecclesia.service.resource.ImageProcessingServiceImpl;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Image processing service integration test
 */
public class ImageProcessingServiceIntegrationTest {
    
    private static final String PRIVATE_FOLDER = "/tmp/ecclesia_test/private";
    private static final String PUBLIC_FOLDER = "/tmp/ecclesia_test/public";
    private static final String PUBLIC_FILE_SERVER = "http://www.example.com/public";
    private static final String SERVER_URL = "http://localhost";
    private static final String TEST_IMAGE = "src/test/resources/test-data/images/500x500.png";
            
    private FileService fileService;
    
    private ImageProcessingService instance;
    
    @AfterClass
    public static void classCleanup() throws IOException {
        FileUtils.deleteDirectory(new File(PRIVATE_FOLDER));
        FileUtils.deleteDirectory(new File(PUBLIC_FOLDER));
    }
    
    @Before
    public void initialize() throws IOException {
        fileService = new FileServiceImpl(PRIVATE_FOLDER, PUBLIC_FOLDER,
                PUBLIC_FILE_SERVER, SERVER_URL, new Tika());
        instance = new ImageProcessingServiceImpl(fileService);
    }
    
    @Test
    public void shouldGenerateStyledImages() throws Exception {
        File file = new File(TEST_IMAGE);
        URI publicPath = fileService.save(file, new URI("public:///derived/images"));
        
        instance.processImage(publicPath);
        
        Map<String, URI> styledImages = instance.getGeneratedImagesPaths(publicPath);
        for (URI uri : styledImages.values()) {
            File imageFile = fileService.get(uri);
            assertNotNull(imageFile);
        }
    }
    
}
