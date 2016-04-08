package org.earelin.ecclesia.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repositories.GroupRepository;

/**
 *
 */
@Repository
@Transactional
public class GroupRepositoryDAO implements GroupRepository {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public GroupRepositoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
}
