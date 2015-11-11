package org.earelin.ecclesia.web.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Create organization form adapter
 */
public class CreateOrganizationFormAdapter {
    
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
