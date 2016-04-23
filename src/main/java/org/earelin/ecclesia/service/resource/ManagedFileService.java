package org.earelin.ecclesia.service.resource;

import org.earelin.ecclesia.service.dto.resource.ManagedFileDTO;

/**
 * Managed files service
 */
public interface ManagedFileService {
    void add(ManagedFileDTO file);
    void update(ManagedFileDTO file);
    void remove(long id);
    ManagedFileDTO get(long id);
}
