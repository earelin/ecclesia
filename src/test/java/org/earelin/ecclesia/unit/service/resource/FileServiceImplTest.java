package org.earelin.ecclesia.unit.service.resource;

import java.io.File;
import java.io.IOException;
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
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * FileServiceImpl unit testing
 */
public class FileServiceImplTest {
    
    public static final String PRIVATE_FOLDER = "/tmp/ecclesia_test/private";
    public static final String PUBLIC_FOLDER = "/tmp/ecclesia_test/public";
    public static final String PUBLIC_FILE_SERVER = "http://www.example.com/public";
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
        Tika mockedTika = mock(Tika.class);
        instance = new FileServiceImpl(PRIVATE_FOLDER, PUBLIC_FOLDER,
                PUBLIC_FILE_SERVER, mockedTika);
    }

    @Test
    public void shouldSaveAFileInPublicScheme() throws Exception {
        String folderUri = "public:///testing/folder";
        File file = new File(SAMPLE_FILE);
        String savedFileUri = instance.save(file, folderUri);
        
        String savedFileExceptedPath = PUBLIC_FOLDER + "/testing/folder/sample.txt";
        assertTrue(Files.exists(Paths.get(savedFileExceptedPath)));
        assertEquals(folderUri + "/sample.txt", savedFileUri);
    }
    
    @Test
    public void shouldSaveAFileInPrivateScheme() throws Exception {
        String folderUri = "private:///testing/folder";
        File file = new File(SAMPLE_FILE);
        String savedFileUri = instance.save(file, folderUri);
        
        String savedFileExceptedPath = PRIVATE_FOLDER + "/testing/folder/sample.txt";
        assertTrue(Files.exists(Paths.get(savedFileExceptedPath)));
        assertEquals(folderUri + "/sample.txt", savedFileUri);
    }
    
    @Test
    public void shouldSaveAFileInPublicSchemeWithoutOvewriting() throws Exception {
        String folderUri = "public:///testing/overwriting";
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
        String folderUri = "private:///testing/overwriting";
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
    public void shouldResolvePrivateUriToPathAndUrl() throws Exception {
        String privateFileUri = "private:///folder/example.txt";
        String resolvedFileUri = instance.getPath(privateFileUri);
        String resolvedFileUrl = instance.getUrl(privateFileUri);
        
        assertEquals(PRIVATE_FOLDER + "/folder/example.txt", resolvedFileUri);
        assertEquals("/files/folder/example.txt", resolvedFileUrl);
    }
    
    @Test
    public void shouldResolvePublicUriToPathAndUrl() throws Exception {
        String publicFileUri = "public:///folder/example.txt";
        String resolvedFileUri = instance.getPath(publicFileUri);
        String resolvedFileUrl = instance.getUrl(publicFileUri);
        
        assertEquals(PUBLIC_FOLDER + "/folder/example.txt", resolvedFileUri);
        assertEquals(PUBLIC_FILE_SERVER + "/folder/example.txt", resolvedFileUrl);
    }
    
    @Test(expected = UnhandledFileProtocol.class)
    public void shouldNotResolveNotHandledProtocolUriGettingPath() throws Exception {
        instance.getPath(ILLEGAL_PROTOCOL_URI);
    }

    @Test(expected = UnhandledFileProtocol.class)
    public void shouldNotResolveNotHandledProtocolUriGettingUrl() throws Exception {
        instance.getUrl(ILLEGAL_PROTOCOL_URI);
    }

    @Test
    public void shouldGetAPublicSavedFile() throws Exception {
        String folderUri = "public:///testing/getting";
        File file = new File(SAMPLE_FILE);
        String savedFileUri = instance.save(file, folderUri);
        
        File gottenFile = instance.get(savedFileUri);
        assertTrue(FileUtils.contentEquals(file, gottenFile));
    }

    @Test
    public void shouldGetAPrivateSavedFile() throws Exception {
        String folderUri = "private:///testing/getting";
        File file = new File(SAMPLE_FILE);
        String savedFileUri = instance.save(file, folderUri);
        
        File gottenFile = instance.get(savedFileUri);
        assertTrue(FileUtils.contentEquals(file, gottenFile));
    }
    
    @Test(expected = FileNotFoundException.class)
    public void shouldRaiseAnErrorIfTryesToGetANotExistingFile() throws Exception {
        instance.get("private://testing/not_exists/sample.txt");
    }
    
    @Test
    public void shouldDeleteAnPrivateExistingFile() throws Exception {
        String folderUri = "private:///testing/deleting";
        File file = new File(SAMPLE_FILE);
        String savedFileUri = instance.save(file, folderUri);
        
        instance.delete(savedFileUri);
        
        assertFalse(Files.exists(Paths.get(savedFileUri)));
    }
    
    @Test
    public void shouldDeleteAnPublicExistingFile() throws Exception {
        String folderUri = "public:///testing/deleting";
        File file = new File(SAMPLE_FILE);
        String savedFileUri = instance.save(file, folderUri);
        
        instance.delete(savedFileUri);
        
        assertFalse(Files.exists(Paths.get(savedFileUri)));
    }
    
    @Test(expected = FileNotFoundException.class)
    public void shouldRaiseAnErrorIfTryesToDeleteANotExistingPrivateFile() throws Exception {
        instance.delete("private://testing/not_exists/sample.txt");
    }
    
    @Test(expected = FileNotFoundException.class)
    public void shouldRaiseAnErrorIfTryesToDeleteANotExistingPublicFile() throws Exception {
        instance.delete("private://testing/not_exists/sample.txt");
    }
    
}
