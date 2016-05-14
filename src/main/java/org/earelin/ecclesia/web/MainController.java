package org.earelin.ecclesia.web;

import org.earelin.ecclesia.web.form.RegisterForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {        

    @RequestMapping(path="/", method=RequestMethod.GET)
    public String index(Model model) {
	RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm", registerForm);
	
	return "home";
    }
    
    @RequestMapping(value="/dashboard", method=RequestMethod.GET)
    public String dashboard(Model model) {
	return "dashboard";
    }

}