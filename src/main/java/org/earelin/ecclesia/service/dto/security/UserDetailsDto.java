package org.earelin.ecclesia.service.dto.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.earelin.ecclesia.service.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 */
public class UserDetailsDto extends UserDto implements UserDetails {
    
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	for (String role : getSystemRoles()) {
	    authorities.add(new SimpleGrantedAuthority(role));
	}
	return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
