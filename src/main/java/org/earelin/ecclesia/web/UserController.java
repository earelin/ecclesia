package org.earelin.ecclesia.web;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
public class UserController {
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Map<String, Object> model) {
        
        if (error != null) {
			model.put("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.put("msg", "You've been logged out successfully.");
		}
        
		return "login_form";
	}
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Map<String, Object> model) {        
		return "register_form";
	}
    
    
    
}
