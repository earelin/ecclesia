package org.earelin.ecclesia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Roles in a organization
 */
@Entity
public class OrganizationRole {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    private Organization organization;

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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    
}
