package org.earelin.ecclesia.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Group entity
 */
@Entity
@Table(name="Groups")
public class Group {
    
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @NotNull
    private Organization organization;
    @ManyToOne
    private Group parent;
    @NotBlank
    private String name;
    private Date created;
    private Date updated;

    public Date getCreated() {
        return created != null ? new Date(created.getTime()) : null;
    }

    public void setCreated(Date created) {
        this.created = created != null ? new Date(created.getTime()) : null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdated() {
        return updated != null ? new Date(updated.getTime()) : null;
    }

    public void setUpdated(Date updated) {
        this.updated = updated != null ? new Date(updated.getTime()) : null;
    }
    
}
