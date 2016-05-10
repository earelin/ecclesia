package org.earelin.ecclesia.unit.service.security;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.User;
import org.earelin.ecclesia.repository.UserRepository;
import org.earelin.ecclesia.service.security.EcclesiaUserDetailsService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsService unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    @Mock
    private UserRepository repository;

    private final Mapper mapper = new DozerBeanMapper();
    private UserDetailsService instance;
    
    @Before
    public void init() {
        instance = new EcclesiaUserDetailsService(repository, mapper);
    }
    
    @Test
    public void getExistingUser() {
        User user = new User();
        
        when(repository.findByUsername("username")).thenReturn(user);
        
        instance.loadUserByUsername("username");
        
        verify(repository).findByUsername("username");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void getNotExistingUser() {
        instance.loadUserByUsername("no existing user");
    }
    
}
