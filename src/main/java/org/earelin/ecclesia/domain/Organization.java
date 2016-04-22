package org.earelin.ecclesia.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.earelin.ecclesia.domain.resource.ManagedFile;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Organization domain class
 */
@Entity
@Table(name="Organizations")
public class Organization {
    
    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    private String name;
    @OneToOne
    private ManagedFile logo;
    private Date created;
    private Date updated;
    
    public boolean equals(Object o) {
        if (o instanceof Organization) {
            if (((Organization) o).getId() == this.getId()) {
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

    public ManagedFile getLogo() {
        return logo;
    }

    public void setLogo(ManagedFile logo) {
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
