package org.earelin.ecclesia.service.dto;

import java.util.Date;

/**
 * User organization data transfer object
 */
public class UserOrganizationDto {

    private long id;
    private OrganizationRoleDto role;
    private OrganizationDto organization;
    private UserDto user;
    private Date created;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final UserOrganizationDto other = (UserOrganizationDto) obj;
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

    public OrganizationRoleDto getRole() {
        return role;
    }

    public void setRole(OrganizationRoleDto role) {
        this.role = role;
    }

    public OrganizationDto getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDto organization) {
        this.organization = organization;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
}
