package org.earelin.ecclesia.integration.service.resource;

import java.io.File;
import java.io.IOException;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.resource.FileService;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * FileService integration test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class FileServiceIntegrationTest {
    
    public static final String TEST_FILES_FOLDER = "src/test/resources/test-data/files";
    
    @Autowired
    private FileService instance;
    
    @Autowired
    private OrganizationService organizationService;
    
    @Test
    public void fileTypeDetection() throws IOException {
        final String[] extensions = {"gif", "jpg", "pdf", "png"};
        final String[] mimeTypes = {"image/gif", "image/jpeg", "application/pdf", "image/png"};
        
        for (int i = 0; i < extensions.length; i++) {
            for (int j = 0; j < extensions.length; j++) {
                File testingFile = new File(TEST_FILES_FOLDER + "/" + extensions[i] + "-sample." + extensions[j]);
                String detectedMimeType = instance.getMimeType(testingFile);
                assertEquals("Mime type not match", mimeTypes[i], detectedMimeType);
            }
        }
    }

}
