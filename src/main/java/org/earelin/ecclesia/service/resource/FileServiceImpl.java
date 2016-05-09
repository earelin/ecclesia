package org.earelin.ecclesia.service.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.earelin.ecclesia.service.exception.FileNotFoundException;
import org.earelin.ecclesia.service.exception.UnhandledFileProtocol;

/**
 * File service
 */
public class FileServiceImpl implements FileService {
    
    private static final String PRIVATE_FILES_URL_PATH = "/files";
    
    private static final String PUBLIC_PROTOCOL = "public";
    private static final String PRIVATE_PROTOCOL = "private";
    
    private final String privateFileFolder;
    private final String publicFileFolder;
    private final String publicFilesBaseURL;
    private final String serverURL;
    
    private final Tika tika;

    public FileServiceImpl(String privateFileFolder, String publicFileFolder,
            String publicFilesBaseURL, String serverURL, Tika tika) throws IOException {
        this.privateFileFolder = privateFileFolder;
        this.publicFileFolder = publicFileFolder;
        this.publicFilesBaseURL = publicFilesBaseURL;
        this.serverURL = serverURL;
        this.tika = tika;
        
        checkFolders();       
    }
    
    /**
     * Checks if the folders exists and are writable if the folders does not
     * exist it creates them.
     */
    private void checkFolders() throws IOException {
        // Check private files folder
        final Path privatePath = Paths.get(privateFileFolder);
        if (!Files.exists(privatePath)) {
            Files.createDirectories(privatePath);
        }

        final Path publicPath = Paths.get(publicFileFolder);
        if (!Files.exists(publicPath)) {
            Files.createDirectories(publicPath);
        }
    }
    
    /**
     * Resolves an internal uri to a file system path
     * @param uri
     * @return 
     */
    private String resolveUriToPath(URI uri) throws UnhandledFileProtocol, URISyntaxException {      
        final String scheme = uri.getScheme();
        
        if (scheme.equals(PUBLIC_PROTOCOL)) {
            return publicFileFolder + uri.getPath();
        }
        
        if (scheme.equals(PRIVATE_PROTOCOL)) {
            return privateFileFolder + uri.getPath();
        }
        
        throw new UnhandledFileProtocol("The protocol " + uri.getScheme() + " is nor registered");
    }
    
    /**
     * Resolves an internal uri to the public url
     * @param uri
     * @return 
     */
    private URL resolveUriToUrl(URI uri) throws UnhandledFileProtocol, URISyntaxException, MalformedURLException {
        final String scheme = uri.getScheme();
        
        if (scheme.equals(PUBLIC_PROTOCOL)) {
            return new URL(publicFilesBaseURL + uri.getPath());
        }
        
        if (scheme.equals(PRIVATE_PROTOCOL)) {
            return new URL(serverURL + PRIVATE_FILES_URL_PATH + uri.getPath());
        }
        
        throw new UnhandledFileProtocol("The protocol " + uri.getScheme() + " is nor registered");
    }

    @Override
    public File create(URI uri) throws Exception {
        String filePath = resolveUriToPath(uri);
        Path folderPath = Paths.get(FilenameUtils.getFullPath(filePath));
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }        
        return new File(filePath);
    }

    @Override
    public File get(URI uri) throws Exception {
        final String stringPath = resolveUriToPath(uri);
        Path path = Paths.get(stringPath);
        
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The file " + uri.toString() + " is not found");
        }
        
        return new File(stringPath);
    }

    @Override
    public URL getUrl(URI uri) throws Exception {
        return resolveUriToUrl(uri);
    }

    @Override
    public String getMimeType(File file) throws Exception {
        return tika.detect(file);
    }

    @Override
    public URI save(File file, URI folderUri) throws Exception {
        final String folderFilesystemPath = resolveUriToPath(folderUri);
        final Path folderPath = Paths.get(folderFilesystemPath);
        
        // Create folder if no exists
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        
        // Check colisions with other
        String fileName = file.getName();
        String fileStringPath = folderFilesystemPath + '/' + fileName;
        Path filePath = Paths.get(fileStringPath);

        if (Files.exists(filePath)) {
            int i = 1;
            String tmpFileName;
            
            do {
                String fileExtension = FilenameUtils.getExtension(fileName);
                String fileBaseName = FilenameUtils.getBaseName(fileName);
                tmpFileName = fileBaseName + '_' + i + '.' + fileExtension;
                i++;
            } while (Files.exists(Paths.get(folderFilesystemPath + '/' + tmpFileName)));
            
            fileName = tmpFileName;
            filePath = Paths.get(folderFilesystemPath + '/' + tmpFileName);
        }
        
        Files.copy(new FileInputStream(file), filePath);
        
        return new URI(folderUri.toString() + '/' + fileName);
    }

    @Override
    public void delete(URI uri) throws Exception {
        final String filesystemStringPath = resolveUriToPath(uri);
        Path filesystemPath = Paths.get(filesystemStringPath);
        
        if (!Files.exists(filesystemPath)) {
            throw new FileNotFoundException("The file " + uri.toString() + " is not found");
        }
        
        Files.delete(filesystemPath);
    }

}
