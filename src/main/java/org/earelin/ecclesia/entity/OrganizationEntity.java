package org.earelin.ecclesia.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.earelin.ecclesia.entity.resource.ManagedImageEntity;

/**
 * Organization repository entity
 */
@Entity
@Table(name="Organizations")
public class OrganizationEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToOne
    private ManagedImageEntity logo;
    private Date created;
    private Date updated;

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

    public ManagedImageEntity getLogo() {
        return logo;
    }

    public void setLogo(ManagedImageEntity logo) {
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
