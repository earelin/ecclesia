package org.earelin.ecclesia.service.resource;

import java.io.File;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;

/**
 * Managed files service
 */
public interface ManagedFileService {
    void add(File file);
    void remove(long id);
    ManagedFileDto get(long id);
}
