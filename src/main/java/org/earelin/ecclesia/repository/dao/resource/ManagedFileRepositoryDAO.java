package org.earelin.ecclesia.repository.dao.resource;

import org.earelin.ecclesia.domain.resource.ManagedFile;
import org.earelin.ecclesia.repository.resource.ManagedFileRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ManagedFile data access object
 */
@Repository
public class ManagedFileRepositoryDAO implements ManagedFileRepository {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public ManagedFileRepositoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ManagedFile get(long id) {
        return (ManagedFile) currentSession()
                .get(ManagedFile.class, id);
    }

    @Override
    public void add(ManagedFile file) {
        currentSession().save(file);
    }

    @Override
    public void update(ManagedFile file) {
        currentSession().update(file);
    }

    @Override
    public void remove(ManagedFile file) {
        currentSession().delete(file);
    }

}
