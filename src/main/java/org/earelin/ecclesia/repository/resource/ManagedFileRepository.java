package org.earelin.ecclesia.repository.resource;

import org.earelin.ecclesia.domain.resource.ManagedFile;

/**
 * Managed files repository
 */
public interface ManagedFileRepository {
    ManagedFile get(long id);
    void add(ManagedFile file);
    void update(ManagedFile file);
    void remove(ManagedFile file);
}
