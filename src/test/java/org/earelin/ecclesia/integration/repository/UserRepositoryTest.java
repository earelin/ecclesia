package org.earelin.ecclesia.integration.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.earelin.ecclesia.domain.User;
import org.earelin.ecclesia.repository.UserRepository;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User Repository integration test
 */
@ContextConfiguration(locations = "classpath:spring-test-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository repository;
    
    private User user;
    
    @Before
    public void init() {
        user = new User();
        user.setUsername("User name");
        user.setPassword("password");
        user.setEmail("user@example.com");
    }
    
    @Test
    public void shouldAddAnUser() {
        repository.add(user);
        
        List<User> users = repository.findAll();
        assertNotEquals(0, user.getId());
        assertEquals(1, users.size());
        assertThat(user, samePropertyValuesAs(users.get(0)));
    }
    
    @Test
    public void shouldUpdateAnUser() {       
        repository.add(user);
        
        user.setUsername("Updated user name");        
        repository.update(user);
        
        List<User> users = repository.findAll();
        assertEquals(1, users.size());
        assertThat(user, samePropertyValuesAs(users.get(0)));
    }
    
    @Test
    public void shouldDeleteAnUser() {       
        repository.add(user);
        
        repository.remove(user);
        
        List<User> users = repository.findAll();
        assertEquals(0, users.size());        
    }
    
    @Test
    public void shouldGetAnUser() {       
        repository.add(user);
        
        User savedUser = repository.get(user.getId());
        
        assertThat(user, samePropertyValuesAs(savedUser));
    }
    
    @Test
    public void shouldGetAnUserByName() {       
        repository.add(user);
        
        User savedUser = repository.findByUsername("User name");
        
        assertThat(user, samePropertyValuesAs(savedUser));
    }
    
}
