package org.earelin.ecclesia.web.admin;

import java.util.Map;
import org.earelin.ecclesia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
public class UserAdminController {
    
    private final UserService service;

    @Autowired
    public UserAdminController(UserService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
        model.put("users", service.list());
		return "admin_user_index";
	}
    
    
}
