package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.criteria.FilteringCriteria;
import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.earelin.ecclesia.service.dto.UserDto;

/**
 * User service
 * 
 * @author Xavier Carriba
 * @since 0.1
 */
public interface UserService {
    UserDto register(String username, String email, String password);
    void update(UserDto user);
    void remove(long id);
    UserDto get(long id);
    boolean isUsernameUsed(String name);
    List<UserDto> findAll(FilteringCriteria filtering, OrderingCriteria ordering, int limit, int offset);
}
