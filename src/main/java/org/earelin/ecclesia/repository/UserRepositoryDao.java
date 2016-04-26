package org.earelin.ecclesia.repository;

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
    public User findByUsername(String username) {
        return (User) currentSession()
                .createQuery("from User as u where u.username = :username")
                .setString("username", username)
                .uniqueResult();
    }

}
