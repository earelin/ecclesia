package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.earelin.ecclesia.dao.UserDAO;
import org.earelin.ecclesia.domain.User;
import org.earelin.ecclesia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    
    private final UserDAO dao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User authenticate(String username, String password) {
        return dao.authenticate(username, password);
    }

    /**
     * User registration process
     * @param username
     * @param email
     * @param password 
     */
    @Override
    public void register(String username, String email, String password) {
        Date now = new Date();
        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setAdmin(false);
        user.setCreated(now);
        
        //Set password with encoding if a encoder is set
        if (passwordEncoder != null) {
            user.setPassword(passwordEncoder.encode(password));
        } else {
            user.setPassword(password);
        }

        dao.add(user);
    }

    @Override
    public List<User> list() {
        return dao.list();
    }

    @Override
    public List<User> list(int limit, int offset) {
        return dao.list(limit, offset);
    }

}
