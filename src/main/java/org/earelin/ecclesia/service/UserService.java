/*
 * 
 */
package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.entities.UserEntity;

/**
 * 
 */
public interface UserService {
    public void register(String username, String email, String password);
    public List<UserEntity> list();
    public List<UserEntity> list(int limit, int offset);
}
