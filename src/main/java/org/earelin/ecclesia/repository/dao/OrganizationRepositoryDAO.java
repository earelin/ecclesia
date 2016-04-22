package org.earelin.ecclesia.repository.dao;

import java.util.List;
import org.earelin.ecclesia.domain.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repository.OrganizationReponsitory;

/**
 *
 */
@Repository
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
    public Organization get(long id) {
        return (Organization) currentSession()
                .get(Organization.class, id);
    }

    @Override
    public void add(Organization organization) {
        currentSession().save(organization);
    }
    
    @Override
    public void update(Organization organization) {
        currentSession().saveOrUpdate(organization);
    }

    @Override
    public void remove(Organization organization) {
        currentSession().delete(organization);
    }

    @Override
    public List<Organization> list() {
        return (List<Organization>) currentSession()
                .createQuery("from Organization as org order by org.name")
                .list();
    }

    @Override
    public List<Organization> list(int limit, int offset) {
        return (List<Organization>) currentSession()
                .createQuery("from Organization as org order by org.name")
                .setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }
    
}
