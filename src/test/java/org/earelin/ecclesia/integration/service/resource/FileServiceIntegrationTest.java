package org.earelin.ecclesia.integration.service.resource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.earelin.ecclesia.service.exception.FileNotFoundException;
import org.earelin.ecclesia.service.exception.UnhandledFileProtocol;
import org.earelin.ecclesia.service.resource.FileServiceImpl;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * FileServiceImpl unit testing
 */
public class FileServiceIntegrationTest {
    
    public static final String PRIVATE_FOLDER = "/tmp/ecclesia_test/private";
    public static final String PUBLIC_FOLDER = "/tmp/ecclesia_test/public";
    public static final String PUBLIC_FILE_SERVER = "http://www.example.com/public";
    public static final String SERVER_URL = "http://localhost";
    public static final String EXAMPLE_FILE_NAME = "example.txt";
    public static final String ILLEGAL_PROTOCOL_URI = "illegal:///folder/example.txt";
    public static final String SAMPLE_FILE = "src/test/resources/test-data/files/sample.txt";
            
    FileServiceImpl instance;
    
    @AfterClass
    public static void classCleanup() throws IOException {
        FileUtils.deleteDirectory(new File(PRIVATE_FOLDER));
        FileUtils.deleteDirectory(new File(PUBLIC_FOLDER));
    }
    
    @Before
    public void initialize() throws IOException {
        instance = new FileServiceImpl(PRIVATE_FOLDER, PUBLIC_FOLDER,
                PUBLIC_FILE_SERVER, SERVER_URL, new Tika());
    }
    
    @Ignore
    @Test
    public void fileTypeDetection() throws Exception {
//        final String[] extensions = {"gif", "jpg", "pdf", "png"};
//        final String[] mimeTypes = {"image/gif", "image/jpeg", "application/pdf", "image/png"};
//        
//        for (int i = 0; i < extensions.length; i++) {
//            for (int j = 0; j < extensions.length; j++) {
//                File testingFile = new File(TEST_FILES_FOLDER + "/" + extensions[i] + "-sample." + extensions[j]);
//                String detectedMimeType = instance.getMimeType(testingFile);
//                assertEquals("Mime type not match", mimeTypes[i], detectedMimeType);
//            }
//        }
    }
    
    @Test
    public void shouldCreateAFile() throws Exception {
        File file = instance.create(new URI("public:///creating/file/file.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("Testing file");
        writer.close();
        assertTrue("File should be created", Files.exists(Paths.get(PUBLIC_FOLDER + "/creating/file/file.txt")));
    }

    @Test
    public void shouldSaveAFileInPublicScheme() throws Exception {
        URI folderUri = new URI("public:///testing/folder");
        File file = new File(SAMPLE_FILE);
        URI savedFileUri = instance.save(file, folderUri);
        
        String savedFileExceptedPath = PUBLIC_FOLDER + "/testing/folder/sample.txt";
        assertTrue(Files.exists(Paths.get(savedFileExceptedPath)));
        assertEquals(new URI(folderUri + "/sample.txt"), savedFileUri);
    }
    
    @Test
    public void shouldSaveAFileInPrivateScheme() throws Exception {
        URI folderUri = new URI("private:///testing/folder");
        File file = new File(SAMPLE_FILE);
        URI savedFileUri = instance.save(file, folderUri);
        
        String savedFileExceptedPath = PRIVATE_FOLDER + "/testing/folder/sample.txt";
        assertTrue(Files.exists(Paths.get(savedFileExceptedPath)));
        assertEquals(new URI(folderUri + "/sample.txt"), savedFileUri);
    }
    
    @Test
    public void shouldSaveAFileInPublicSchemeWithoutOvewriting() throws Exception {
        URI folderUri = new URI("public:///testing/overwriting");
        File file = new File(SAMPLE_FILE);
        instance.save(file, folderUri);
        instance.save(file, folderUri);
        instance.save(file, folderUri);
        
        String savedFileExceptedPath = PUBLIC_FOLDER + "/testing/overwriting/sample_1.txt";
        assertTrue(Files.exists(Paths.get(savedFileExceptedPath)));
        savedFileExceptedPath = PUBLIC_FOLDER + "/testing/overwriting/sample_2.txt";
        assertTrue(Files.exists(Paths.get(savedFileExceptedPath)));
    }
    
    @Test
    public void shouldSaveAFileInPrivateSchemeWithoutOvewriting() throws Exception {
        URI folderUri = new URI("private:///testing/overwriting");
        File file = new File(SAMPLE_FILE);
        instance.save(file, folderUri);
        instance.save(file, folderUri);
        instance.save(file, folderUri);
        
        String savedFileExceptedPath = PRIVATE_FOLDER + "/testing/overwriting/sample_1.txt";
        assertTrue(Files.exists(Paths.get(savedFileExceptedPath)));
        savedFileExceptedPath = PRIVATE_FOLDER + "/testing/overwriting/sample_2.txt";
        assertTrue(Files.exists(Paths.get(savedFileExceptedPath)));
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
        instance.getUrl(new URI(ILLEGAL_PROTOCOL_URI));
    }

    @Test
    public void shouldGetAPublicSavedFile() throws Exception {
        URI folderUri = new URI("public:///testing/getting");
        File file = new File(SAMPLE_FILE);
        URI savedFileUri = instance.save(file, folderUri);
        
        File gottenFile = instance.get(savedFileUri);
        assertTrue(FileUtils.contentEquals(file, gottenFile));
    }

    @Test
    public void shouldGetAPrivateSavedFile() throws Exception {
        URI folderUri = new URI("private:///testing/getting");
        File file = new File(SAMPLE_FILE);
        URI savedFileUri = instance.save(file, folderUri);
        
        File gottenFile = instance.get(savedFileUri);
        assertTrue(FileUtils.contentEquals(file, gottenFile));
    }
    
    @Test(expected = FileNotFoundException.class)
    public void shouldRaiseAnErrorIfTryesToGetANotExistingFile() throws Exception {
        instance.get(new URI("private://testing/not_exists/sample.txt"));
    }
    
    @Test
    public void shouldDeleteAnPrivateExistingFile() throws Exception {
        URI folderUri = new URI("private:///testing/deleting");
        File file = new File(SAMPLE_FILE);
        URI savedFileUri = instance.save(file, folderUri);
        
        instance.delete(savedFileUri);
        
        assertFalse(Files.exists(Paths.get(PRIVATE_FOLDER + savedFileUri.getPath())));
    }
    
    @Test
    public void shouldDeleteAnPublicExistingFile() throws Exception {
        URI folderUri = new URI("public:///testing/deleting");
        File file = new File(SAMPLE_FILE);
        URI savedFileUri = instance.save(file, folderUri);
        
        instance.delete(savedFileUri);
        
        assertFalse(Files.exists(Paths.get(PUBLIC_FOLDER + savedFileUri)));
    }
    
    @Test(expected = FileNotFoundException.class)
    public void shouldRaiseAnErrorIfTryesToDeleteANotExistingPrivateFile() throws Exception {
        instance.delete(new URI("private://testing/not_exists/sample.txt"));
    }
    
    @Test(expected = FileNotFoundException.class)
    public void shouldRaiseAnErrorIfTryesToDeleteANotExistingPublicFile() throws Exception {
        instance.delete(new URI("private://testing/not_exists/sample.txt"));
    }
    
}
