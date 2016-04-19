package org.earelin.ecclesia.integration.service;

import javax.validation.ConstraintViolationException;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.service.exception.UserNotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * UserService unit test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class UserServiceIntegrationTest {
    
    private static final String USER_NAME = "Testing organization";
    
    @Autowired
    private UserService instance;
    
    @Ignore
    @Test
    public void createNewUser() {
        
    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveNotBlankName() {

    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void newUserShouldHaveValidEmail() {

    }
    
    @Ignore
    @Test
    public void getExistingUser() {
        
    }
    
    @Ignore
    @Test(expected = UserNotFoundException.class)
    public void getNotExistingUser() {
        
    }
    
    @Ignore
    @Test
    public void updatingExistingUser() {
        
    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void updatedUserShouldHaveNotBlankName() {

    }
    
    @Ignore
    @Test(expected = ConstraintViolationException.class)
    public void updatedUserShouldHaveValidEmail() {

    }
    
    @Ignore
    @Test(expected = UserNotFoundException.class)
    public void removeExistingUser() {
        
    }
    
}
