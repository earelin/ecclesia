package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.User;

/**
 * User repository
 */
public interface UserRepository {
    void add(User user);
    void update(User user);
    void remove(User user);
    User get(long id);
    List<User> list();
    List<User> list(int limit, int offset);
    User loadUserByUsername(String string);
}
