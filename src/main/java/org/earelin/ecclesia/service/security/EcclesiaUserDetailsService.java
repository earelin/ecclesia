package org.earelin.ecclesia.service.security;

import org.dozer.Mapper;
import org.earelin.ecclesia.entity.User;
import org.earelin.ecclesia.repository.UserRepository;
import org.earelin.ecclesia.service.dto.security.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Custom implementation of Spring Security UserDetailsService
 */
@Service
@Transactional
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
        
        UserDetailsDTO userDetails = mapper.map(user, UserDetailsDTO.class);
        return userDetails;
    }

}
