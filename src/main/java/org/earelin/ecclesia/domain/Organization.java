package org.earelin.ecclesia.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.earelin.ecclesia.domain.resources.ManagedImage;

/**
 * Organizations
 */
@Entity
public class Organization {
    
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToOne
    private ManagedImage logo;
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

    public ManagedImage getLogo() {
        return logo;
    }

    public void setLogo(ManagedImage logo) {
        this.logo = logo;
    }
    
}
