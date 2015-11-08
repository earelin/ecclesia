package org.earelin.ecclesia.security;

import java.util.ArrayList;
import java.util.List;
import org.earelin.ecclesia.domain.User;
import org.earelin.ecclesia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    
    private final UserService userService;
    
    @Autowired
    public AuthenticationProviderImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) 
            throws AuthenticationException {
        
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.authenticate(name, password);
        
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        if (user.isAdmin()) {
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        
        return auth;
    }   

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
