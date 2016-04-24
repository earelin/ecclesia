package org.earelin.ecclesia.service.impl.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.tika.Tika;
import org.earelin.ecclesia.service.exception.UnhandledFileProtocol;
import org.earelin.ecclesia.service.resource.FileService;

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
    private String resolveUriToPath(String uri) throws UnhandledFileProtocol {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Resolves an internal uri to the public url
     * @param uri
     * @return 
     */
    private String resolveUriToUrl(String uri) throws UnhandledFileProtocol {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public File get(String uri) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl(String uri) throws UnhandledFileProtocol {
        return resolveUriToUrl(uri);
    }

    @Override
    public String getPath(String uri) throws UnhandledFileProtocol {
        return resolveUriToPath(uri);
    }

    @Override
    public String getMimeType(File file) throws IOException {
        return tika.detect(file);
    }

    @Override
    public String save(File file, String folderUri) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(String uri) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
