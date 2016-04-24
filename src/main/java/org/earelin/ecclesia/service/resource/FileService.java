package org.earelin.ecclesia.service.resource;

import java.io.File;
import java.io.IOException;
import org.earelin.ecclesia.service.exception.UnhandledFileProtocol;

/**
 * File service
 *
 * Manage low level file operations 
 */
public interface FileService {
    File get(String uri) throws IOException, UnhandledFileProtocol;
    String getUrl(String uri) throws UnhandledFileProtocol;
    String getPath(String uri) throws UnhandledFileProtocol;
    String getMimeType(File file) throws IOException;
    String save(File file, String folderUri) throws IOException, UnhandledFileProtocol;
    String delete(String uri) throws IOException, UnhandledFileProtocol;
}
