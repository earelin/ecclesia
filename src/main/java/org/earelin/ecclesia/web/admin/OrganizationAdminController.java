package org.earelin.ecclesia.web.admin;

import java.util.List;
import java.util.Map;
import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.web.form.EditOrganizationForm;
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
    
    @RequestMapping(value = "/admin/organizations", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
      List<OrganizationDto> organizations = service.findAll(null, new OrderingCriteria("name"), 0, 0);
      model.put("organizations", organizations);
      return "admin/organizations";
    }
    
    @RequestMapping(value = "/admin/organizations/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {
      EditOrganizationForm organizationForm = new EditOrganizationForm();
      model.put("organizationForm", organizationForm);
      return "admin/organizations-add";
    }
    
    @RequestMapping(value = "/admin/organizations/add", method = RequestMethod.POST)
      public String addPost(Map<String, Object> model) {
      return "admin/organizations-add";
    }
    
    @RequestMapping(value = "/admin/organizations/{organization_id}/edit", method = RequestMethod.GET)
      public String edit(Map<String, Object> model) {
      return "admin/organizations-add";
    }
    
    @RequestMapping(value = "/admin/organizations/{organization_id}/edit", method = RequestMethod.POST)
      public String editPost(Map<String, Object> model) {
      return "admin/organizations-add";
    }
    
}
