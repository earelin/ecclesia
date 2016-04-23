package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.User;

/**
 * User repository
 */
public interface UserRepository extends GenericRepository<User> {
    List<User> findAll(int limit, int offset);
    User findByUsername(String string);
}
