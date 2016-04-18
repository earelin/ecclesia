package org.earelin.ecclesia.web.admin;

import java.util.Map;
import org.earelin.ecclesia.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 */
@Controller
public class OrganizationAdminController {

    private final OrganizationService service;
    
    @Autowired
    public OrganizationAdminController(OrganizationService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/admin/organization", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
        model.put("organizations", service.list());
		return "admin_organization_index";
	}
    
}
