package org.earelin.ecclesia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.earelin.ecclesia.entities.resources.ManagedImageEntity;

/**
 * Organizations
 */
@Entity
public class OrganizationEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToOne
    private ManagedImageEntity logo;
    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public ManagedImageEntity getLogo() {
        return logo;
    }

    public void setLogo(ManagedImageEntity logo) {
        this.logo = logo;
    }
    
}
