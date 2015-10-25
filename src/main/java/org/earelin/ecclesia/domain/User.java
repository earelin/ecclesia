package org.earelin.ecclesia.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.earelin.ecclesia.domain.resources.ManagedImage;

/**
 * Users of the system
 */
@Entity
@Table(name="Users")
public class User {
    
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;
    private String email;
    private Date created;
    private Date lastLogin;
    private Boolean bloked;  
    @OneToOne
    private ManagedImage avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getBloked() {
        return bloked;
    }

    public void setBloked(Boolean bloked) {
        this.bloked = bloked;
    }

    public ManagedImage getAvatar() {
        return avatar;
    }

    public void setAvatar(ManagedImage avatar) {
        this.avatar = avatar;
    }
}
