package org.earelin.ecclesia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    
    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public AuthenticationProviderImpl(UserDetailsService userService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) 
            throws AuthenticationException {
        
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        UserDetails user = userService.loadUserByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        
        Authentication auth = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        
        return auth;
    }   

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
