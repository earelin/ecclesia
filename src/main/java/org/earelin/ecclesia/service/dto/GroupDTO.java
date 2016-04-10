package org.earelin.ecclesia.service.dto;

import java.util.Date;

/**
 * Group data transfer object class
 */
public class GroupDTO {

    private long id;
    private OrganizationDTO organization;
    private GroupDTO parent;
    private String name;
    private Date created;
    private Date updated;
    
    public GroupDTO() {}
    
    public GroupDTO(OrganizationDTO organization, String name) {
        this.organization = organization;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GroupDTO) {
            GroupDTO group = (GroupDTO) o;
            if (this.id == group.getId()) {
                return true;
            }
        }
        return false;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public GroupDTO getParent() {
        return parent;
    }

    public void setParent(GroupDTO parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
