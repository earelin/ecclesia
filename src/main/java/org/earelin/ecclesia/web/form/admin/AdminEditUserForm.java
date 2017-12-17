package org.earelin.ecclesia.web.form.admin;

import java.net.URL;
import java.util.List;

/**
 * Edit user form for admins
 * 
 * @author xcarriba
 * @since 0.1
 */
public class AdminEditUserForm {

    private long id;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private URL avatarUrl;
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

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public URL getAvatarUrl() {
	return avatarUrl;
    }

    public void setAvatarUrl(URL avatarUrl) {
	this.avatarUrl = avatarUrl;
    }

    public List<String> getSystemRoles() {
	return systemRoles;
    }

    public void setSystemRoles(List<String> systemRoles) {
	this.systemRoles = systemRoles;
    }
    
}
