package org.earelin.ecclesia.repository.dao;

import java.util.List;
import org.earelin.ecclesia.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repository.UserRepository;

/**
 *
 */
@Repository
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
    public User loadUserByUsername(String username) {
        return (User) currentSession()
                .createQuery("from User as u where u.username = :username")
                .setString("username", username)
                .uniqueResult();
    }

    @Override
    public User get(long id) {
        return (User) currentSession()
                .get(User.class, id);
    }

}
