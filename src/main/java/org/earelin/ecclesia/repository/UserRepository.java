/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.User;

/**
 *
 * @author xcarriba
 */
public interface UserRepository {
    void add(User user);
    void update(User user);
    void remove(User user);
    User get(long id);
    List<User> list();
    List<User> list(int limit, int offset);
    User loadUserByUsername(String string);
}
