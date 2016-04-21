package org.earelin.ecclesia.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.earelin.ecclesia.entity.User;
import org.earelin.ecclesia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repository.UserRepository;
import org.earelin.ecclesia.service.dto.UserDTO;
import org.earelin.ecclesia.service.exception.UserNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * User service implementation
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository dao,  Mapper mapper, 
            PasswordEncoder passwordEncoder) {
        this.repository = dao;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    /**
     * User registration process
     * @param username
     * @param email
     * @param password 
     */
    @Override
    public UserDTO register(String username, String email, String password) {
        Date now = new Date();
        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setCreated(now);
        user.setUpdated(now);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setSystemRoles(new ArrayList(Arrays.asList("USER_ROLE")));
        
        repository.add(user);
        
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public void update(UserDTO userDTO) {
        long userId = userDTO.getId();
        User user = repository.get(userId);
        
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        
        userDTO.setUpdated(new Date());
        mapper.map(userDTO, user);
        repository.update(user);
    }

    @Override
    public void remove(long id) {
        User user = repository.get(id);
        
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        
        repository.remove(user);
    }

    @Override
    public UserDTO get(long id) {
        User user = repository.get(id);
        
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        
        return mapper.map(user, UserDTO.class);
    }
    
    @Override
    public List<UserDTO> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserDTO> list(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
