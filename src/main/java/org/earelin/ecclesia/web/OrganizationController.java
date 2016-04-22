package org.earelin.ecclesia.web;

import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 */
@Controller
public class OrganizationController {
    
    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService service) {
        this.service = service;
    }
    
    @RequestMapping(path="/organization/{organizationId}", method=RequestMethod.GET)
    public String show(@PathVariable("organizationId") long organizationId,
            Model model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
