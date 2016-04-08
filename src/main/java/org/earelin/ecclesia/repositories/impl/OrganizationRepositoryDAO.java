package org.earelin.ecclesia.repositories.impl;

import java.util.List;
import org.earelin.ecclesia.entities.OrganizationEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repositories.OrganizationReponsitory;

/**
 *
 */
@Repository
@Transactional
public class OrganizationRepositoryDAO implements OrganizationReponsitory {

    private final SessionFactory sessionFactory;
    
    @Autowired
    public OrganizationRepositoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public OrganizationEntity get(long id) {
        return (OrganizationEntity) currentSession()
                .get(OrganizationEntity.class, id);
    }

    @Override
    public void add(OrganizationEntity organization) {
        currentSession().save(organization);
    }
    
    @Override
    public void update(OrganizationEntity organization) {
        currentSession().saveOrUpdate(organization);
    }

    @Override
    public void remove(OrganizationEntity organization) {
        currentSession().delete(organization);
    }

    @Override
    public List<OrganizationEntity> list() {
        return (List<OrganizationEntity>) currentSession()
                .createQuery("from Organization as org order by org.name")
                .list();
    }

    @Override
    public List<OrganizationEntity> list(int limit, int offset) {
        return (List<OrganizationEntity>) currentSession()
                .createQuery("from Organization as org order by org.name")
                .setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }
    
}
