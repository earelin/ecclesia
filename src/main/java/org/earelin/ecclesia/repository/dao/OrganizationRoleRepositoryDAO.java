package org.earelin.ecclesia.repository.dao;

import org.earelin.ecclesia.domain.OrganizationRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repository.OrganizationRoleRepository;

/**
 * Organization role data access object
 */
@Repository
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

    @Override
    public OrganizationRole get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
