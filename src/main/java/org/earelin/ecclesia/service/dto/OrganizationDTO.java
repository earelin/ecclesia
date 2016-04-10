package org.earelin.ecclesia.service.dto;

import java.util.Date;
import org.earelin.ecclesia.service.dto.resouce.ManagedImageDTO;

/**
 * Organization data transfer object class
 */
public class OrganizationDTO {

    private long id = 0;
    private String name;
    private Date created;
    private Date updated;
    private ManagedImageDTO logo;
    
    public OrganizationDTO() {}
    
    public OrganizationDTO(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof OrganizationDTO) {
            OrganizationDTO organization = (OrganizationDTO) o;
            if (this.id == organization.getId()) {
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

    public ManagedImageDTO getLogo() {
        return logo;
    }

    public void setLogo(ManagedImageDTO logo) {
        this.logo = logo;
    }

}
