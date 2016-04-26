package org.earelin.ecclesia.repository;

import org.earelin.ecclesia.domain.User;

/**
 * User repository
 */
public interface UserRepository extends GenericRepository<User> {
    User findByUsername(String string);
}
