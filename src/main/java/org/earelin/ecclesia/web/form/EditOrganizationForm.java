package org.earelin.ecclesia.web.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Create organization form adapter
 */
public class EditOrganizationForm {
    
    private long id = 0;
    private String name;
    private String description;

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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
    
}
