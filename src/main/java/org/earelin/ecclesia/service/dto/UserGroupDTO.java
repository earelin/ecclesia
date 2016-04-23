package org.earelin.ecclesia.service.dto;

import java.util.Date;

/**
 * User group data transfer object
 */
public class UserGroupDTO {

    private long id;
    private GroupRoleDTO role;
    private GroupDTO group;
    private UserDTO user;
    private Date created;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserGroupDTO other = (UserGroupDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GroupRoleDTO getRole() {
        return role;
    }

    public void setRole(GroupRoleDTO role) {
        this.role = role;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
}
