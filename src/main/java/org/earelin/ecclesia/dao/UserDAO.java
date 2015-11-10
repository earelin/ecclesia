/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.dao;

import java.util.List;
import org.earelin.ecclesia.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author xcarriba
 */
public interface UserDAO {
    public void add(User user);
    public void update(User user);
    public void remove(User user);
    public List<User> list();
    public List<User> list(int limit, int offset);
    public UserDetails loadUserByUsername(String string);
}
