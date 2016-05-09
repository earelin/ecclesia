package org.earelin.ecclesia.unit.service.security;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.repository.UserRepository;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.service.dto.UserDto;
import org.earelin.ecclesia.service.security.EcclesiaUserDetailsService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsService unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    @Mock
    private UserService userService;
    
    @Mock
    private UserRepository repository;

    private final Mapper mapper = new DozerBeanMapper();
    private UserDetailsService instance;
    
    @Before
    public void init() {
        instance = new EcclesiaUserDetailsService(repository, mapper);
    }
    
    @Ignore
    @Test
    public void getExistingUser() {
//        UserDto user = userService.register(USER_NAME, USER_EMAIL, USER_PASSWORD);
//        UserDto gottenUser = (UserDto) instance.loadUserByUsername(USER_NAME);
//        assertEquals("Register user id should be the same as loaded by username",
//                user.getId(), gottenUser.getId());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void getNotExistingUser() {
        instance.loadUserByUsername("no existing user");
    }
    
}
