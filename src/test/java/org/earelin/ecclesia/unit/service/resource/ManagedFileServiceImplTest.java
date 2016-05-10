package org.earelin.ecclesia.unit.service.resource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.earelin.ecclesia.domain.resource.ManagedFile;
import org.earelin.ecclesia.repository.resource.ManagedFileRepository;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;
import org.earelin.ecclesia.service.dto.resource.ManagedImageDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.resource.FileService;
import org.earelin.ecclesia.service.resource.ImageProcessingService;
import org.earelin.ecclesia.service.resource.ManagedFileService;
import org.earelin.ecclesia.service.resource.ManagedFileServiceImpl;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * ManagedFileServiceImpl unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class ManagedFileServiceImplTest {
    
    private static final String NORMAL_FILE_PATH = "src/test/resources/test-data/files/pdf-sample.pdf";
    
    private ManagedFileService instance;
    
    @Mock
    private FileService fileService;
    
    @Mock
    private ManagedFileRepository repository;
    
    @Mock
    private ImageProcessingService imageService;
    
    @Before
    public void init() {
        instance = new ManagedFileServiceImpl(repository, fileService, imageService);
    }

    @Test
    public void shouldStoreFilesInAGivenUriFolder() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                ManagedFile managedFile = (ManagedFile) args[0];
                managedFile.setId(1);
                return null;
            } 
        }).when(repository).add(any(ManagedFile.class));
        
        final URI folderUri = new URI("private:///managed-file/add/to/uri");
        final URI fileUri = new URI("private:///managed-file/add/to/uri/sample.txt");
        final String fileMime = "text/utf8";
        final URL fileUrl = new URL("http://cdb.example.com/managed-file/add/to/uri/sample.txt");
        File file = mock(File.class);
        
        when(fileService.getMimeType(file)).thenReturn(fileMime);
        when(fileService.save(file, folderUri)).thenReturn(fileUri);
        when(fileService.getUrl(fileUri)).thenReturn(fileUrl);        
        
        Date beforeCreate = new Date();
        ManagedFileDto managedFile = instance.add(file, folderUri);
        Date afterCreate = new Date();
        
        assertTrue(managedFile.getId() != 0);
        assertEquals(fileMime, managedFile.getMime());
        assertTrue(managedFile.getCreated().compareTo(beforeCreate) >= 0
                && managedFile.getCreated().compareTo(afterCreate) <= 0);
        assertEquals(fileUrl, managedFile.getUrl());        
    }
    
    @Ignore
    @Test
    public void shouldStorePublicFilesWithAnAutomaticPattern() throws Exception {
        
    }
    
    @Ignore
    @Test
    public void shouldStorePrivateFilesWithAnAutomaticPattern() throws Exception {
        
    }
    
    @Test
    public void shouldRemoveFiles() throws Exception {
        URI fileUri = new URI("public:///some/file.txt");
        ManagedFile file = new ManagedFile();
        file.setUri(fileUri);
        file.setMime("text/utf8");
        
        when(repository.get(1)).thenReturn(file);
                
        instance.remove(1);
        
        verify(fileService).delete(fileUri);
        verify(repository).remove(file);
    }
    
    @Test
    public void shouldRemoveImages() throws Exception {
        URI fileUri = new URI("public:///some/file.jpg");
        ManagedFile file = new ManagedFile();
        file.setUri(fileUri);
        file.setMime("image/jpeg");
        
        when(repository.get(1)).thenReturn(file);
                
        instance.remove(1);
        
        verify(fileService).delete(fileUri);
        verify(imageService).deleteGeneratedImages(fileUri);
        verify(repository).remove(file);        
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingFile() throws Exception {
        instance.remove(1);
    }
    
    @Test
    public void shouldReturnFiles() throws URISyntaxException, Exception {
        URL fileUrl = new URL("http://cdn.example.com/file.txt");
        
        ManagedFile file = new ManagedFile();
        file.setId(1);
        file.setCreated(new Date());
        file.setUri(new URI("public:///some/file.txt"));
        file.setMime("text/utf8");
        
        when(repository.get(1)).thenReturn(file);
        when(fileService.getUrl(file.getUri())).thenReturn(fileUrl);
        
        ManagedFileDto managedFile = instance.get(1);
        
        assertEquals(file.getId(), managedFile.getId());
        assertEquals(file.getMime(), managedFile.getMime());
        assertEquals(file.getCreated(), managedFile.getCreated());
        assertEquals(fileUrl, managedFile.getUrl());
        verify(repository).get(file.getId());
        verify(fileService).getUrl(file.getUri());
    }
    
    @Test
    public void shouldReturnImages()
            throws URISyntaxException, MalformedURLException, Exception {
        URL fileUrl = new URL("http://cdn.example.com/file.jpg");
        
        ManagedFile file = new ManagedFile();
        file.setId(1);
        file.setCreated(new Date());
        file.setUri(new URI("public:///some/file.jpg"));
        file.setMime("image/jpeg");
        
        Map<String, URL> imageStyle = new HashMap();
        imageStyle.put("style1", new URL("http://cdn.example.com/style1/file.jpg"));
        imageStyle.put("style2", new URL("http://cdn.example.com/style2/file.jpg"));
        
        when(repository.get(1)).thenReturn(file);
        when(fileService.getUrl(file.getUri())).thenReturn(fileUrl);
        when(imageService.getGeneratedImagesUrls(file.getUri())).thenReturn(imageStyle);
        
        ManagedImageDto managedImage = (ManagedImageDto) instance.get(1);
        
        assertTrue(managedImage instanceof ManagedImageDto);
        assertEquals(file.getId(), managedImage.getId());
        assertEquals(file.getMime(), managedImage.getMime());
        assertEquals(file.getCreated(), managedImage.getCreated());
        assertEquals(fileUrl, managedImage.getUrl());
        assertSame(imageStyle.get("style1"), managedImage.getStyledUrl("style1"));
        assertSame(imageStyle.get("style2"), managedImage.getStyledUrl("style2"));
        verify(repository).get(file.getId());
        verify(fileService).getUrl(file.getUri());        
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingFile() throws Exception {
        instance.get(1);
    }
    
}
