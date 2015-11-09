/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.domain.User;

/**
 *
 * @author xcarriba
 */
public interface UserService {
    public User authenticate(String username, String password);
    public void register(String username, String email, String password);
    public List<User> list();
    public List<User> list(int limit, int offset);
}
