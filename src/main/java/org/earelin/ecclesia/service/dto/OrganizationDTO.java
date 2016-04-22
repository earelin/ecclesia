package org.earelin.ecclesia.service.dto;

import java.util.Date;
import org.earelin.ecclesia.service.dto.resource.ManagedFileDTO;

/**
 * Organization data transfer object
 */
public class OrganizationDTO {
    
    private long id;
    private String name;
    private ManagedFileDTO logo;
    private Date created;
    private Date updated;
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof OrganizationDTO) {
            if (((OrganizationDTO) o).getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

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

    public ManagedFileDTO getLogo() {
        return logo;
    }

    public void setLogo(ManagedFileDTO logo) {
        this.logo = logo;
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
