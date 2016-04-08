package org.earelin.ecclesia.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Roles of a group
 */
@Entity
public class GroupRoleEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    private GroupEntity group;

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

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
   
}
