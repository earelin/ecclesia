package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.earelin.ecclesia.dao.UserDAO;
import org.earelin.ecclesia.domain.User;
import org.earelin.ecclesia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    
    private final UserDAO dao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(password));
        
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = dao.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return user;
    }

}
