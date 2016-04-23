package org.earelin.ecclesia.repository.dao;

import java.util.List;
import org.earelin.ecclesia.domain.User;
import org.springframework.stereotype.Repository;
import org.earelin.ecclesia.repository.UserRepository;

/**
 * User data access object
 */
@Repository
public class UserRepositoryDao  extends GenericDaoImpl<User>
        implements UserRepository {

    @Override
    public List<User> findAll(int limit, int offset) {
        return (List<User>) currentSession()
                .createQuery("from User as u order by u.username")
                .setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }

    @Override
    public User findByUsername(String username) {
        return (User) currentSession()
                .createQuery("from User as u where u.username = :username")
                .setString("username", username)
                .uniqueResult();
    }

}