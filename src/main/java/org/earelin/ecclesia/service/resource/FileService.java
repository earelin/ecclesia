package org.earelin.ecclesia.service.resource;

import java.io.File;

/**
 * File service
 *
 * Manage low level file operations 
 */
public interface FileService {
    String getMimeType(File file);
    String getFile(String uri);
    String getFileUrl(String uri);
    String getFilePath(String uri);
    String saveFile(File file, String folderUri);
}
