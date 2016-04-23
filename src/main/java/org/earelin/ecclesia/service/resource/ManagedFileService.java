package org.earelin.ecclesia.service.resource;

import org.earelin.ecclesia.service.dto.resource.ManagedFileDto;

/**
 * Managed files service
 */
public interface ManagedFileService {
    void add(ManagedFileDto file);
    void update(ManagedFileDto file);
    void remove(long id);
    ManagedFileDto get(long id);
}
