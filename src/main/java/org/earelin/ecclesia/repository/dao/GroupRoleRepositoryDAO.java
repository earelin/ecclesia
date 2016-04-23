package org.earelin.ecclesia.repository.dao;

import org.earelin.ecclesia.domain.GroupRole;
import org.earelin.ecclesia.repository.GroupRoleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Group role data access object
 */
@Repository
public class GroupRoleRepositoryDAO implements GroupRoleRepository {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public GroupRoleRepositoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(GroupRole role) {
        currentSession().save(role);
    }

    @Override
    public void update(GroupRole role) {
        currentSession().update(role);
    }

    @Override
    public void remove(GroupRole role) {
        currentSession().delete(role);
    }

    @Override
    public GroupRole get(long id) {
        return (GroupRole) currentSession()
                .get(GroupRole.class, id);
    }

}
