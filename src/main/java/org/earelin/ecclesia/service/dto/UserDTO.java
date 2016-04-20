package org.earelin.ecclesia.service.dto;

import java.util.Date;
import java.util.List;
import org.earelin.ecclesia.service.dto.resource.ManagedImageDTO;

/**
 *
 */
public class UserDTO {
    
    private long id;
    private String username;
    private String password;
    private String email;
    private Date created;
    private Date updated;
    private Date lastLogin;
    private boolean enabled;
    private boolean admin;
    private ManagedImageDTO avatar;
    private List<String> systemRoles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ManagedImageDTO getAvatar() {
        return avatar;
    }

    public void setAvatar(ManagedImageDTO avatar) {
        this.avatar = avatar;
    }

    public List<String> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(List<String> systemRoles) {
        this.systemRoles = systemRoles;
    }
    
}