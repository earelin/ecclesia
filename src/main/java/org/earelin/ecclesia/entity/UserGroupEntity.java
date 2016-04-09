package org.earelin.ecclesia.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name="UserGroups")
public class UserGroupEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private GroupRoleEntity role;
    @ManyToOne
    private GroupEntity group;
    @ManyToOne
    private UserEntity user;
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GroupRoleEntity getRole() {
        return role;
    }

    public void setRole(GroupRoleEntity role) {
        this.role = role;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
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
