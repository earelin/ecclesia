package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.service.dto.UserDTO;

/**
 * 
 */
public interface UserService {
    UserDTO register(String username, String email, String password);
    void update(UserDTO user);
    void remove(long id);
    UserDTO get(long id);
    List<UserDTO> list();
    List<UserDTO> list(int limit, int offset);
}
