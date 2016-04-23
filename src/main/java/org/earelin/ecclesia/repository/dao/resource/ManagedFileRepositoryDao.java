package org.earelin.ecclesia.repository.dao.resource;

import org.earelin.ecclesia.domain.resource.ManagedFile;
import org.earelin.ecclesia.repository.dao.GenericDaoImpl;
import org.earelin.ecclesia.repository.resource.ManagedFileRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ManagedFile data access object
 */
@Repository
public class ManagedFileRepositoryDao  extends GenericDaoImpl<ManagedFile>
        implements ManagedFileRepository {    
    
}
