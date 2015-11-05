package org.earelin.ecclesia.dao.impl;

import org.earelin.ecclesia.dao.UserDAO;
import org.earelin.ecclesia.domain.User;
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
public class UserDAOImpl implements UserDAO {
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User loadUserByUsername(String username) {
        return (User) currentSession()
                .createQuery("from User as u where u.username = ?")
                .setString(0, username)
                .uniqueResult();
    }

}