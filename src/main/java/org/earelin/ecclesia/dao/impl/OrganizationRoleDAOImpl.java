package org.earelin.ecclesia.dao.impl;

import org.earelin.ecclesia.dao.OrganizationRoleDAO;
import org.earelin.ecclesia.domain.OrganizationRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Repository
@Transactional
public class OrganizationRoleDAOImpl implements OrganizationRoleDAO {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public OrganizationRoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(OrganizationRole role) {
        currentSession().save(role);
    }

    @Override
    public void update(OrganizationRole role) {
        currentSession().update(role);
    }

    @Override
    public void remove(OrganizationRole role) {
        currentSession().delete(role);
    }

}
