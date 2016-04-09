package org.earelin.ecclesia.repository.impl;

import java.util.List;
import org.earelin.ecclesia.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repository.UserRepository;

/**
 *
 */
@Repository
@Transactional
public class UserRepositoryDAO implements UserRepository {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public UserRepositoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public void add(User user) {
        currentSession().save(user);
    }
    
    @Override
    public void update(User user) {
        currentSession().saveOrUpdate(user);
    }

    @Override
    public void remove(User user) {
        currentSession().delete(user);
    }

    @Override
    public List<User> list() {
        return (List<User>) currentSession()
                .createQuery("from User as u order by u.username")
                .list();
    }

    @Override
    public List<User> list(int limit, int offset) {
        return (List<User>) currentSession()
                .createQuery("from User as u order by u.username")
                .setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return (UserDetails) currentSession()
                .createQuery("from User as u where u.username = :username")
                .setString("username", username)
                .uniqueResult();
    }

}
