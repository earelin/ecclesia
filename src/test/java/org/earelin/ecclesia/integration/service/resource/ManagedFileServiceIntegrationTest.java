package org.earelin.ecclesia.integration.service.resource;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.plexus.util.FileUtils;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.resource.ImageProcessingService;
import org.earelin.ecclesia.service.resource.ManagedFileService;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ManagedFileService integration test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class ManagedFileServiceIntegrationTest {
    
    public static final String NORMAL_FILE_PATH = "src/test/resources/test-data/files/pdf-sample.pdf";
    public static final String IMAGE_FILE_PATH = "src/test/resources/test-data/files/jpg-sample.jpg";
    
    @Autowired
    private ManagedFileService instance;
    
    @Autowired
    private ImageProcessingService imageService;

    @Ignore
    @Test
    public void shouldStoreFilesInAGivenUriFolder() throws Exception {
        // TODO implement method
        final URI folderUri = new URI("private:///managed-file/add/to/uri");
        File file = new File(NORMAL_FILE_PATH);
        
        Date beforeCreate = new Date();
        ManagedFileDto managedFile = instance.add(file, folderUri);
        Date afterCreate = new Date();
        
//        final URI parsedUri = new URI(managedFile.getUrl());
//        String parsedPath = FilenameUtils.getFullPath(parsedUri.getPath());
//        
//        final String storedFilePath = "/tmp/ecclesia_folder/private/" + parsedUri.getPath();
//        File storedFile = new File(storedFilePath);
        
        assertTrue("ManagedFileDto should have a not null id",
                managedFile.getId() != 0);
        assertEquals("ManagedFileDto should have correct mime type",
                "application/pdf", managedFile.getMime());
        assertTrue("Created file created field should have current date", 
                managedFile.getCreated().compareTo(beforeCreate) >= 0
                && managedFile.getCreated().compareTo(afterCreate) <= 0);
//        assertEquals("ManagedFile folder", folderUri, "private://" + parsedPath);
//        assertTrue("File has been created", Files.exists(Paths.get(storedFilePath)));
//        assertEquals("File contents are the same", FileUtils.contentEquals(file, storedFile));                       
    }
    
    @Ignore
    @Test
    public void shouldStorePublicFilesWithAnAutomaticPattern() throws Exception {
        File file = new File(NORMAL_FILE_PATH);
        ManagedFileDto managedFile = instance.addPublicFile(file);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateString = formatter.format(LocalDateTime.now());
        
        String expectedFolder = "private:///" + dateString;
        
        
        
    }
    
    @Ignore
    @Test
    public void shouldStorePrivateFilesWithAnAutomaticPattern() throws Exception {
        File file = new File(NORMAL_FILE_PATH);
        ManagedFileDto managedFile = instance.addPublicFile(file);
        
    }
    
    @Ignore
    @Test
    public void shouldRemoveFiles() {
        File file = new File(NORMAL_FILE_PATH);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingFile() throws Exception {
        instance.remove(100000);
    }
    
    @Ignore
    @Test
    public void shouldReturnFiles() {
        File file = new File(NORMAL_FILE_PATH);
    }
    
    @Ignore
    @Test
    public void shouldReturnImages() {
        File file = new File(IMAGE_FILE_PATH);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingFile() throws Exception {
        instance.get(100000);
    }
    
}
