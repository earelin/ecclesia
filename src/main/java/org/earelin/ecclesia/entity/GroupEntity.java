package org.earelin.ecclesia.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Groups
 */
@Entity
@Table(name="Groups")
public class GroupEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private OrganizationEntity organization;
    @ManyToOne
    private GroupEntity parent;
    private String name;
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

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public GroupEntity getParent() {
        return parent;
    }

    public void setParent(GroupEntity parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
