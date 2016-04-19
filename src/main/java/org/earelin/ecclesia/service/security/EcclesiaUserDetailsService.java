package org.earelin.ecclesia.service.security;

import org.dozer.Mapper;
import org.earelin.ecclesia.entity.User;
import org.earelin.ecclesia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Custom implementation of Spring Security UserDetailsService
 */
@Component
public class EcclesiaUserDetailsService implements UserDetailsService {
    
    private final UserRepository repository;
    private final Mapper mapper;
    
    @Autowired
    public EcclesiaUserDetailsService(UserRepository repository,  Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = repository.loadUserByUsername(string);
        
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
