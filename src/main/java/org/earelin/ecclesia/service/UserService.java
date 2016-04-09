/*
 * 
 */
package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.entity.User;

/**
 * 
 */
public interface UserService {
    public void register(String username, String email, String password);
    public List<User> list();
    public List<User> list(int limit, int offset);
}
