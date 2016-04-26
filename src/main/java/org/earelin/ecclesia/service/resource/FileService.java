package org.earelin.ecclesia.service.resource;

import java.io.File;

/**
 * File service
 *
 * Manage low level file operations 
 */
public interface FileService {
    File get(String uri) throws Exception;
    String getUrl(String uri) throws Exception;
    String getPath(String uri) throws Exception;
    String getMimeType(File file) throws Exception;

    /**
     * Stores a file in the passed folder and returns the uri of the saved file
     * @param file
     * @param folderUri
     * @return
     * @throws Exception
     */
    String save(File file, String folderUri) throws Exception;
    void delete(String uri) throws Exception;
}
