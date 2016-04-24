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
    String save(File file, String folderUri) throws Exception;
    void delete(String uri) throws Exception;
}
