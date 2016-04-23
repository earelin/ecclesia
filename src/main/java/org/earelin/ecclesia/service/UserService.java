package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.service.dto.UserDto;

/**
 * 
 */
public interface UserService {
    UserDto register(String username, String email, String password);
    void update(UserDto user);
    void remove(long id);
    UserDto get(long id);
    List<UserDto> list();
    List<UserDto> list(int limit, int offset);
}
