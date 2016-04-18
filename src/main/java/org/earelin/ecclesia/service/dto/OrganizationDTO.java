package org.earelin.ecclesia.service.dto;

import java.util.Date;
import org.earelin.ecclesia.service.dto.resource.ManagedImageDTO;

/**
 * Organization data transfer object
 */
public class OrganizationDTO {
    
    private long id;
    private String name;
    private ManagedImageDTO logo;
    private Date created;
    private Date updated;
    
    public boolean equals(Object o) {
        if (o instanceof OrganizationDTO) {
            if (((OrganizationDTO) o).getId() == this.getId()) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ManagedImageDTO getLogo() {
        return logo;
    }

    public void setLogo(ManagedImageDTO logo) {
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
