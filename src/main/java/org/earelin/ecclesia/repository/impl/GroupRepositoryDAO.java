package org.earelin.ecclesia.repository.impl;

import java.util.List;
import org.earelin.ecclesia.entity.Group;
import org.earelin.ecclesia.entity.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repository.GroupRepository;

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

    @Override
    public Group get(long id) {
        return (Group) currentSession().get(Group.class, id);
    }

    @Override
    public void add(Group group) {
        currentSession().save(group);
    }

    @Override
    public void update(Group group) {
        currentSession().saveOrUpdate(group);
    }

    @Override
    public void remove(Group group) {
        currentSession().delete(group);
    }

    @Override
    public List<Group> list(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Group> list(Organization organization, int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
