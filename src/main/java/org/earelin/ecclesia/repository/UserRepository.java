/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.entity.User;

/**
 *
 * @author xcarriba
 */
public interface UserRepository {
    public void add(User user);
    public void update(User user);
    public void remove(User user);
    public User get(long id);
    public List<User> list();
    public List<User> list(int limit, int offset);
    public User loadUserByUsername(String string);
}
