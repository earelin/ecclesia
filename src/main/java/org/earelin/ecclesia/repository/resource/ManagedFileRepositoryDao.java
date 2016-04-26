package org.earelin.ecclesia.repository.resource;

import org.earelin.ecclesia.domain.resource.ManagedFile;
import org.earelin.ecclesia.repository.GenericRepositoryDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * ManagedFile data access object
 */
@Repository
public class ManagedFileRepositoryDao  extends GenericRepositoryDaoImpl<ManagedFile>
        implements ManagedFileRepository {    
    
}
