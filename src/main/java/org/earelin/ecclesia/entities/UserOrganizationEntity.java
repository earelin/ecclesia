package org.earelin.ecclesia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 */
@Entity
public class UserOrganizationEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private OrganizationRoleEntity role;
    @ManyToOne
    private OrganizationEntity organization;
    @ManyToOne
    private UserEntity user;
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrganizationRoleEntity getRole() {
        return role;
    }

    public void setRole(OrganizationRoleEntity role) {
        this.role = role;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
}
