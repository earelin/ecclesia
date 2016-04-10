/*
 * 
 */
package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.service.dto.UserDTO;

/**
 * 
 */
public interface UserService {
    public UserDTO register(String username, String email, String password);
    public List<UserDTO> list();
    public List<UserDTO> list(int limit, int offset);
}
