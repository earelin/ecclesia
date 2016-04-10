package org.earelin.ecclesia.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.earelin.ecclesia.entity.resource.ManagedImage;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Organization repository entity
 */
@Entity
@Table(name="Organizations")
public class Organization implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    private String name;
    @OneToOne
    private ManagedImage logo;
    private Date created;
    private Date updated;

    public Organization() {}
    
    public Organization(String name) {
        this.name = name;
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

    public ManagedImage getLogo() {
        return logo;
    }

    public void setLogo(ManagedImage logo) {
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
