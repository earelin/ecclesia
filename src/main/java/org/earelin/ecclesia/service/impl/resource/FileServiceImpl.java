package org.earelin.ecclesia.service.impl.resource;

import java.io.File;
import org.earelin.ecclesia.service.resource.FileService;

/**
 * File service
 */
public class FileServiceImpl implements FileService {
    
    private final String privateFileFolder;
    private final String publicFileFolder;
    private final String publicFilesURL;

    public FileServiceImpl(String privateFileFolder, String publicFileFolder,
            String publicFilesURL) {
        this.privateFileFolder = privateFileFolder;
        this.publicFileFolder = publicFileFolder;
        this.publicFilesURL = publicFilesURL;
    }

    @Override
    public String getMimeType(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFileUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
