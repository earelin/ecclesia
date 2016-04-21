package org.earelin.ecclesia.integration.service;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.service.dto.UserDTO;
import org.earelin.ecclesia.service.exception.UserNotFoundException;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * UserService unit test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class UserServiceIntegrationTest {
    
    private static final String USER_NAME = "testing user";
    private static final String USER_EMAIL = "user@localhost.local";
    private static final String USER_PASSWORD = "secret";
    
    @Autowired
    private UserService instance;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void registerNewUser() {
        Date beforeRegister = new Date();
        UserDTO user = instance.register(USER_NAME, USER_EMAIL, USER_PASSWORD);
        Date afterRegister = new Date();
        
        assertNotSame("Registered user id should not be 0", 0, user.getId());
        assertTrue("Registered user created field should have current date", 
                user.getCreated().compareTo(beforeRegister) >= 0
                && user.getCreated().compareTo(afterRegister) <= 0);
        assertEquals("Registered user updated field should have the same value as created field", 
                user.getCreated(), user.getUpdated());
        assertEquals("Registered user name should be equals to submited", 
                USER_NAME, user.getUsername());
        assertEquals("Registered user email should be equals to submited", 
                USER_EMAIL, user.getEmail());
        assertTrue("Registered user encrypted password should match with submited",
                passwordEncoder.matches(USER_PASSWORD, user.getPassword()));
    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveNotBlankName() {
        instance.register("  ", USER_EMAIL, USER_PASSWORD);
    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveNotBlankEmail() {
        instance.register("  ", USER_EMAIL, USER_PASSWORD);
    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveValidEmail() {
        instance.register(USER_NAME, "adsfad", USER_PASSWORD);
    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveNotBlankPassword() {
        instance.register(USER_NAME, USER_EMAIL, "  ");
    }
    
    @Ignore
    @Test
    public void getExistingUser() {
         UserDTO user = instance.register(USER_NAME, USER_EMAIL, USER_PASSWORD);         
         user = instance.get(user.getId());
    }
    
    @Test(expected = UserNotFoundException.class)
    public void getNotExistingUser() {
        instance.get(100000);
    }
    
    @Ignore
    @Test
    public void updatingExistingUser() {
        
    }
    
    @Ignore
    @Test
    public void updatingNotExistingUser() {
        
    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void updatedUserShouldHaveNotBlankName() {

    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void updatedUserShouldHaveValidEmail() {

    }
    
    @Test(expected = UserNotFoundException.class)
    public void removeExistingUser() {
        UserDTO user = instance.register(USER_NAME, USER_EMAIL, USER_PASSWORD);
        long userId = user.getId();
        instance.remove(userId);
        instance.get(userId);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void removeNotExistingUser() {
        instance.remove(100000);
    }
    
}
