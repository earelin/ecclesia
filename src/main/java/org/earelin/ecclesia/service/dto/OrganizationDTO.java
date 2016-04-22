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
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final OrganizationDTO other = (OrganizationDTO) obj;
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
