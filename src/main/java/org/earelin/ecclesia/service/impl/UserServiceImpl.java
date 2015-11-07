package org.earelin.ecclesia.service.impl;

import org.earelin.ecclesia.dao.UserDAO;
import org.earelin.ecclesia.domain.User;
import org.earelin.ecclesia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    
    private final UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public User authenticate(String username, String password) {
        return dao.authenticate(username, password);
    }

}
