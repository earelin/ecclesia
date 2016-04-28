package org.earelin.ecclesia.service.resource;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * File service
 *
 * Manage low level file operations 
 */
public interface FileService {
    File create(URI uri) throws Exception;
    File get(URI uri) throws Exception;
    URL getUrl(URI path) throws Exception;
    String getMimeType(File file) throws Exception;

    /**
     * Stores a file in the passed folder and returns the uri of the saved file
     * @param file
     * @param folderUri
     * @return
     * @throws Exception
     */
    URI save(File file, URI folderUri) throws Exception;
    void delete(URI uri) throws Exception;
}
