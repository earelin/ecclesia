package org.earelin.ecclesia.service.resource;

import java.io.File;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;

/**
 * Managed files service
 */
public interface ManagedFileService {
    void add(File file) throws Exception;
    void add(File file, String folderUri) throws Exception;
    void remove(long id) throws Exception;
    ManagedFileDto get(long id);
}
