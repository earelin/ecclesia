package org.earelin.ecclesia.service.dto;

import java.util.Date;

/**
 * Group data transfer object
 */
public class GroupDTO {
    
    private long id;
    private OrganizationDTO organization;
    private GroupDTO parent;
    private String name;
    private Date created;
    private Date updated;

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
    
}
