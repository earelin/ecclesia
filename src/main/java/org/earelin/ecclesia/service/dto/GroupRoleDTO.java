package org.earelin.ecclesia.service.dto;

/**
 * Group role data transfer object
 */
public class GroupRoleDTO {
    
    private long id;
    private String name;
    private GroupDTO group;

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

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }
    
}
