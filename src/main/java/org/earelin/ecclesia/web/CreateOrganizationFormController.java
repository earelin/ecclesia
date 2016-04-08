package org.earelin.ecclesia.web;

import org.earelin.ecclesia.entities.OrganizationEntity;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.web.form.CreateOrganizationFormAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
public class CreateOrganizationFormController {

    private final OrganizationService service;

    @Autowired
    public CreateOrganizationFormController(OrganizationService service) {
        this.service = service;
    }
    
    @RequestMapping(path="/organization/add", method=RequestMethod.GET)
    public String create(Model model) {
        CreateOrganizationFormAdapter organizationForm 
                = new CreateOrganizationFormAdapter();
        model.addAttribute("organization", organizationForm);
        return "create_organization_form";
    }
    
    @RequestMapping(path="/organization/add", method=RequestMethod.POST)
	public String createSubmit(
            @ModelAttribute("organization") @Validated CreateOrganizationFormAdapter organizationForm,
            BindingResult result) {
        if (result.hasErrors()) {
            return "create_organization_form";
        }
        
        // TODO create the organization and redirect to page
        
        return "redirect:/organization/";
    }
    
}
