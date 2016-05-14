package org.earelin.ecclesia.unit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.earelin.ecclesia.domain.User;
import org.earelin.ecclesia.repository.UserRepository;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.service.UserServiceImpl;
import org.earelin.ecclesia.service.dto.UserDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * UserServiceImpl unit test
 * 
 * @author Xavier Carriba
 * @since 0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    private UserService instance;
    private final Mapper mapper = new DozerBeanMapper();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Mock
    private UserRepository repository;
    
    @Before
    public void init() {
        instance = new UserServiceImpl(repository, mapper, passwordEncoder);
    }
    
    @Test
    public void registerNewUser() {
        String username = "username";
        String email = "username@example.com";
        String password = "password";
        
        doAnswer((Answer) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            User user = (User) args[0];
            user.setId(1);
            return null; 
        }).when(repository).add(any(User.class));                
        
        Date beforeRegister = new Date();
        UserDto user = instance.register(username, email, password);
        Date afterRegister = new Date();
        
        assertNotEquals(0, user.getId());
        assertTrue(user.getCreated().compareTo(beforeRegister) >= 0
                && user.getCreated().compareTo(afterRegister) <= 0);
        assertEquals(user.getCreated(), user.getUpdated());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(user.getSystemRoles(), new ArrayList(Arrays.asList("USER_ROLE")));
        assertTrue(user.isEnabled());        
        assertTrue("Registered user encrypted password should match with submited",
                passwordEncoder.matches(password, user.getPassword()));
        verify(repository).add(any(User.class));
    }

    @Test
    public void getExistingUser() {
        Date now = new Date();
        
        User user = new User();
        user.setId(1);
        user.setCreated(now);
        user.setEmail("user@example.com");
        user.setEnabled(true);
        user.setLastLogin(now);
        user.setPassword("password");
        user.setSystemRoles(new ArrayList<>(Arrays.asList("role1", "role2")));
        user.setUpdated(now);
        user.setUsername("username");
        
        when(repository.get(user.getId())).thenReturn(user);
        
        UserDto userDto = instance.get(user.getId());
        
        verify(repository).get(user.getId());
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getCreated(), userDto.getCreated());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.isEnabled(), userDto.isEnabled());
        assertEquals(user.getLastLogin(), userDto.getLastLogin());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getSystemRoles(), userDto.getSystemRoles());
        assertEquals(user.getUpdated(), userDto.getUpdated());
        assertEquals(user.getUsername(), userDto.getUsername());
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void getNotExistingUser() {
        instance.get(1);
    }
    
    @Test
    public void updatingExistingUser() {
        UserDto user = new UserDto();
        user.setId(1);
        
        User userEntity = new User();
        when(repository.get(user.getId())).thenReturn(userEntity);
        
        Date beforeUpdate = new Date();
        instance.update(user);
        Date afterUpdate = new Date();
        
        assertTrue(user.getUpdated().compareTo(beforeUpdate) >= 0
                && user.getUpdated().compareTo(afterUpdate) <= 0);
        verify(repository).update(userEntity);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void updatingNotExistingUser() {
        UserDto user = new UserDto();
        user.setId(1);
        
        instance.update(user);
    }
    
    @Test
    public void removeExistingUser() {
        User user = new User();
        
        when(repository.get(1)).thenReturn(user);
        
        instance.remove(1);
        
        verify(repository).remove(user);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void removeNotExistingUser() {
        instance.remove(1);
    }
    
    @Test
    public void testIfAUsernameIsUsed() {
	when(repository.findByUsername("username")).thenReturn(new User());
	
	boolean exists = instance.isUsernameUsed("username");
	
	verify(repository).findByUsername("username");
	assertTrue(exists);
    }
    
    @Test
    public void testIfAUsernameIsNotUsed() {
	when(repository.findByUsername("username")).thenReturn(null);
	
	boolean exists = instance.isUsernameUsed("username");
	
	verify(repository).findByUsername("username");
	assertFalse(exists);
    }
    
}
