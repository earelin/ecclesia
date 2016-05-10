package org.earelin.ecclesia.service;

import org.earelin.ecclesia.service.dto.UserDto;

/**
 * User service
 */
public interface UserService {
    UserDto register(String username, String email, String password);
    void update(UserDto user);
    void remove(long id);
    UserDto get(long id);
}
