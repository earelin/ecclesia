package org.earelin.ecclesia.unit.service;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.repository.UserRepository;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.service.UserServiceImpl;
import org.earelin.ecclesia.service.dto.UserDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * UserServiceImpl unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    private UserService instance;
    private final Mapper mapper = new DozerBeanMapper();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Mock
    private UserRepository repository;
    
    @Before
    public void init() {
        instance = new UserServiceImpl(repository, mapper, passwordEncoder);
    }
    
    @Ignore
    @Test
    public void registerNewUser() {
//        String username = "test user register";
//        
//        Date beforeRegister = new Date();
//        //UserDto user = instance.register(username, USER_EMAIL, USER_PASSWORD);
//        Date afterRegister = new Date();
//        
//        assertNotSame("Registered user id should not be 0", 0, user.getId());
//        assertTrue("Registered user created field should have current date", 
//                user.getCreated().compareTo(beforeRegister) >= 0
//                && user.getCreated().compareTo(afterRegister) <= 0);
//        assertEquals("Registered user updated field should have the same value as created field", 
//                user.getCreated(), user.getUpdated());
//        assertEquals("Registered user name should be equals to submited", 
//                username, user.getUsername());
//        assertEquals("Registered user email should be equals to submited", 
//                USER_EMAIL, user.getEmail());
//        assertTrue("Registered user encrypted password should match with submited",
//                passwordEncoder.matches(USER_PASSWORD, user.getPassword()));
    }

    @Ignore
    @Test
    public void getExistingUser() {
         UserDto user = instance.get(1);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingUser() {
        instance.get(1);
    }
    
    @Ignore
    @Test
    public void updatingExistingUser() {
//        UserDto user = instance.register("test updating existing user", USER_EMAIL, USER_PASSWORD);         
//        String updatedEmail = "updated.user@localhost.local";
//        user.setEmail(updatedEmail);
//        Date beforeUpdate = new Date();
//        instance.update(user);
//        Date afterUpdate = new Date();
//        
//        assertTrue("Updated organization updated field should have current date", 
//                user.getUpdated().compareTo(beforeUpdate) >= 0
//                && user.getUpdated().compareTo(afterUpdate) <= 0);
//        assertEquals(updatedEmail, user.getEmail());
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updatingNotExistingUser() {
        UserDto user = new UserDto();
        user.setId(1);
        
        instance.update(user);
    }
    
    @Ignore
    @Test(expected = EntityNotFoundException.class)
    public void removeExistingUser() {
//        UserDto user = instance.register("test remove existing user", USER_EMAIL, USER_PASSWORD);
//        long userId = user.getId();
//        instance.remove(userId);
//        instance.get(userId);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingUser() {
        instance.remove(1);
    }
    
}
