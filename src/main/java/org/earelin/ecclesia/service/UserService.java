package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.service.dto.UserDTO;

/**
 * 
 */
public interface UserService {
    public UserDTO register(String username, String email, String password);
    public void update(UserDTO user);
    public void remove(long id);
    public UserDTO get(long id);
    public List<UserDTO> list();
    public List<UserDTO> list(int limit, int offset);
}
