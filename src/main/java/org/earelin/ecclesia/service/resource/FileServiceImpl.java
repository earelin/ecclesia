package org.earelin.ecclesia.service.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
    
    private final Tika tika;

    public FileServiceImpl(String privateFileFolder, String publicFileFolder,
            String publicFilesBaseURL, Tika tika) throws IOException {
        this.privateFileFolder = privateFileFolder;
        this.publicFileFolder = publicFileFolder;
        this.publicFilesBaseURL = publicFilesBaseURL;
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
    private String resolveUriToPath(String uri) throws UnhandledFileProtocol, URISyntaxException {
        final URI parsedUri = new URI(uri);        
        final String scheme = parsedUri.getScheme();
        
        if (scheme.equals(PUBLIC_PROTOCOL)) {
            return publicFileFolder + parsedUri.getPath();
        }
        
        if (scheme.equals(PRIVATE_PROTOCOL)) {
            return privateFileFolder + parsedUri.getPath();
        }
        
        throw new UnhandledFileProtocol();
    }
    
    /**
     * Resolves an internal uri to the public url
     * @param uri
     * @return 
     */
    private String resolveUriToUrl(String uri) throws UnhandledFileProtocol, URISyntaxException {
        final URI parsedUri = new URI(uri);        
        final String scheme = parsedUri.getScheme();
        
        if (scheme.equals(PUBLIC_PROTOCOL)) {
            return publicFilesBaseURL + parsedUri.getPath();
        }
        
        if (scheme.equals(PRIVATE_PROTOCOL)) {
            return PRIVATE_FILES_URL_PATH + parsedUri.getPath();
        }
        
        throw new UnhandledFileProtocol();
    }

    @Override
    public File get(String uri) throws Exception {
        final String stringPath = resolveUriToPath(uri);
        Path path = Paths.get(stringPath);
        
        if (!Files.exists(path)) {
            throw new FileNotFoundException();
        }
        
        return new File(stringPath);
    }

    @Override
    public String getUrl(String uri) throws Exception {
        return resolveUriToUrl(uri);
    }

    @Override
    public String getPath(String uri) throws Exception {
        return resolveUriToPath(uri);
    }

    @Override
    public String getMimeType(File file) throws Exception {
        return tika.detect(file);
    }

    @Override
    public String save(File file, String folderUri) throws Exception {
        final String folderStringPath = resolveUriToPath(folderUri);
        final Path folderPath = Paths.get(folderStringPath);
        
        // Create folder if no exists
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        
        // Check colisions with other
        String fileName = file.getName();
        String fileStringPath = folderStringPath + '/' + fileName;
        Path filePath = Paths.get(fileStringPath);

        if (Files.exists(filePath)) {
            int i = 1;
            String tmpFileName;
            
            do {
                String fileExtension = FilenameUtils.getExtension(fileName);
                String fileBaseName = FilenameUtils.getBaseName(fileName);
                tmpFileName = fileBaseName + '_' + i + '.' + fileExtension;
                i++;
            } while (Files.exists(Paths.get(folderStringPath + '/' + tmpFileName)));
            
            fileName = tmpFileName;
            filePath = Paths.get(folderStringPath + '/' + tmpFileName);
        }
        
        Files.copy(new FileInputStream(file), filePath);
        
        return folderUri + '/' + fileName;
    }

    @Override
    public void delete(String uri) throws Exception {
        final String folderStringPath = resolveUriToPath(uri);
        Path filePath = Paths.get(folderStringPath);
        
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException();
        }
        
        Files.delete(filePath);
    }

}
