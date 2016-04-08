package org.earelin.ecclesia.repositories.impl;

import org.earelin.ecclesia.entities.OrganizationRoleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repositories.OrganizationRoleRepository;

/**
 *
 */
@Repository
@Transactional
public class OrganizationRoleRepositoryDAO implements OrganizationRoleRepository {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public OrganizationRoleRepositoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(OrganizationRoleEntity role) {
        currentSession().save(role);
    }

    @Override
    public void update(OrganizationRoleEntity role) {
        currentSession().update(role);
    }

    @Override
    public void remove(OrganizationRoleEntity role) {
        currentSession().delete(role);
    }

}
