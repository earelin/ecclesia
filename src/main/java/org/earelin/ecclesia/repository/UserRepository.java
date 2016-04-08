/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author xcarriba
 */
public interface UserRepository {
    public void add(UserEntity user);
    public void update(UserEntity user);
    public void remove(UserEntity user);
    public List<UserEntity> list();
    public List<UserEntity> list(int limit, int offset);
    public UserDetails loadUserByUsername(String string);
}
