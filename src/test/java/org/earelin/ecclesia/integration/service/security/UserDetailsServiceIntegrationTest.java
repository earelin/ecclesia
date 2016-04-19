package org.earelin.ecclesia.integration.service.security;

import org.earelin.ecclesia.service.UserService;
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
    
    private static final String USER_NAME = "Testing User";
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDetailsService instance;
    
    @Ignore
    @Test
    public void getExistingUser() {
        
    }
    
    @Ignore
    @Test(expected = UsernameNotFoundException.class)
    public void getNotExistingUser() {
        
    }
    
}
