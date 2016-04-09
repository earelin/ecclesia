package org.earelin.ecclesia.domain;

import java.util.Date;

/**
 * Organization domain
 */
public class Organization {

    private long id;
    private String name;
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
