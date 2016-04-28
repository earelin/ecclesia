package org.earelin.ecclesia.service.resource;

import java.io.File;
import java.net.URI;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;

/**
 * Managed files service
 */
public interface ManagedFileService {
    ManagedFileDto addPublicFile(File file) throws Exception;
    ManagedFileDto addPrivateFile(File file) throws Exception;
    ManagedFileDto add(File file, URI folderUri) throws Exception;
    void remove(long id) throws Exception;
    ManagedFileDto get(long id) throws Exception;
}
