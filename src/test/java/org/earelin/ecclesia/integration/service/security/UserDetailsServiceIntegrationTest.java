package org.earelin.ecclesia.integration.service.security;

import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.service.dto.UserDto;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * UserDetailsService test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class UserDetailsServiceIntegrationTest {
    
    private static final String USER_NAME = "testing get existing user";
    private static final String USER_EMAIL = "user1@localhost.local";
    private static final String USER_PASSWORD = "secret";
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDetailsService instance;
    
    @Test
    public void getExistingUser() {
        UserDto user = userService.register(USER_NAME, USER_EMAIL, USER_PASSWORD);
        UserDto gottenUser = (UserDto) instance.loadUserByUsername(USER_NAME);
        assertEquals("Register user id should be the same as loaded by username",
                user.getId(), gottenUser.getId());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void getNotExistingUser() {
        instance.loadUserByUsername("no existing user");
    }
    
}
