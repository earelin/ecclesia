package org.earelin.ecclesia.web;

import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.web.form.RegisterForm;
import org.earelin.ecclesia.web.form.validation.RegisterFormValidator;
import org.passay.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User registration controller
 * 
 * @autor Xavier Carriba
 * @since 0.1
 */
@Controller
public class RegisterFormController {
    
    private final UserService userService;
    private final PasswordValidator passwordValidator;
    
    @Autowired
    public RegisterFormController(UserService service, PasswordValidator passwordValidator) {
        this.userService = service;
	this.passwordValidator = passwordValidator;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new RegisterFormValidator(userService, passwordValidator));
    }
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String register(Model model) {  
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm", registerForm);
	
	return "register";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerSubmit(@ModelAttribute("registerForm") @Validated RegisterForm registerForm,
	    BindingResult result) {
	if (result.hasErrors()) {
	    return "register";
	}

	userService.register(registerForm.getUsername(), registerForm.getEmail(), 
	    registerForm.getPassword());
	
	return "register";
    }
    
}
