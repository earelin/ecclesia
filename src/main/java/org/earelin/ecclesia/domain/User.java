package org.earelin.ecclesia.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.earelin.ecclesia.domain.resource.ManagedFile;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Users of the system
 */
@Entity
@Table(name="Users")
public class User {
    
    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    @Column(unique=true)
    private String username;
    @NotBlank
    private String password;
    @Email
    private String email;
    private Date created;
    private Date updated;
    private Date lastLogin;
    private boolean enabled;
    @OneToOne
    private ManagedFile avatar;
    @ElementCollection
    @CollectionTable(name="SystemRoles", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="role")
    private List<String> systemRoles;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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
        return created != null ? new Date(created.getTime()) : null;
    }

    public void setCreated(Date created) {
        this.created = created != null ? new Date(created.getTime()) : null;
    }

    public Date getUpdated() {
        return updated != null ? new Date(updated.getTime()) : null;
    }

    public void setUpdated(Date updated) {
        this.updated = updated != null ? new Date(updated.getTime()) : null;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public ManagedFile getAvatar() {
        return avatar;
    }

    public void setAvatar(ManagedFile avatar) {
        this.avatar = avatar;
    }

    public boolean isAccountNonExpired() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isAccountNonLocked() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isCredentialsNonExpired() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(List<String> systemRoles) {
        this.systemRoles = systemRoles;
    }

}
