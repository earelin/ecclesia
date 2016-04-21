package org.earelin.ecclesia.integration.service;

import java.util.Date;
import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.service.dto.UserDTO;
import org.earelin.ecclesia.service.exception.UserNotFoundException;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
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
    
    private static final String USER_EMAIL = "user@localhost.local";
    private static final String USER_PASSWORD = "secret";
    
    @Autowired
    private UserService instance;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    public void registerNewUser() {
        String username = "test user register";
        
        Date beforeRegister = new Date();
        UserDTO user = instance.register(username, USER_EMAIL, USER_PASSWORD);
        Date afterRegister = new Date();
        
        assertNotSame("Registered user id should not be 0", 0, user.getId());
        assertTrue("Registered user created field should have current date", 
                user.getCreated().compareTo(beforeRegister) >= 0
                && user.getCreated().compareTo(afterRegister) <= 0);
        assertEquals("Registered user updated field should have the same value as created field", 
                user.getCreated(), user.getUpdated());
        assertEquals("Registered user name should be equals to submited", 
                username, user.getUsername());
        assertEquals("Registered user email should be equals to submited", 
                USER_EMAIL, user.getEmail());
        assertTrue("Registered user encrypted password should match with submited",
                passwordEncoder.matches(USER_PASSWORD, user.getPassword()));
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveNotBlankName() {
        instance.register("  ", USER_EMAIL, USER_PASSWORD);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveNotBlankEmail() {
        instance.register("test register blank email", "  ", USER_PASSWORD);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveValidEmail() {
        instance.register("test register valid email", "adsfad", USER_PASSWORD);
    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveNotBlankPassword() {
        instance.register("test register blank password", USER_EMAIL, "  ");
    }
    
    @Test
    public void getExistingUser() {
         UserDTO user = instance.register("test get existing user", USER_EMAIL, USER_PASSWORD);         
         UserDTO gettedUser = instance.get(user.getId());
         assertThat(user, samePropertyValuesAs(gettedUser));
    }
    
    @Test(expected = UserNotFoundException.class)
    public void getNotExistingUser() {
        instance.get(100000);
    }
    
    @Test
    public void updatingExistingUser() {
        UserDTO user = instance.register("test updating existing user", USER_EMAIL, USER_PASSWORD);         
        String updatedEmail = "updated.user@localhost.local";
        user.setEmail(updatedEmail);
        Date beforeUpdate = new Date();
        instance.update(user);
        Date afterUpdate = new Date();
        
        assertTrue("Updated organization updated field should have current date", 
                user.getUpdated().compareTo(beforeUpdate) >= 0
                && user.getUpdated().compareTo(afterUpdate) <= 0);
        assertEquals(updatedEmail, user.getEmail());
    }
    
    @Test(expected = UserNotFoundException.class)
    public void updatingNotExistingUser() {
        UserDTO user = new UserDTO();
        user.setId(100000);
        user.setUsername("test updating not existing user");
        user.setEmail("updated.not.existing.user@localhost.local");
        instance.update(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void updatedUserShouldHaveNotBlankName() {
        UserDTO user = instance.register("test updating user blank name", USER_EMAIL, USER_PASSWORD);
        user.setUsername("   ");
        instance.update(user);
    }

    @Test(expected = org.hibernate.exception.ConstraintViolationException.class)
    public void updatedUserShouldHaveValidEmail() {
        UserDTO user = instance.register("test updating user blank name", USER_EMAIL, USER_PASSWORD);
        user.setEmail("sadfasd");
        instance.update(user);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void removeExistingUser() {
        UserDTO user = instance.register("test remove existing user", USER_EMAIL, USER_PASSWORD);
        long userId = user.getId();
        instance.remove(userId);
        instance.get(userId);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void removeNotExistingUser() {
        instance.remove(100000);
    }
    
}
