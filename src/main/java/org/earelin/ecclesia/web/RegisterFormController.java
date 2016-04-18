package org.earelin.ecclesia.web;

import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.web.form.RegisterFormAdapter;
import org.earelin.ecclesia.web.form.validation.RegisterFormAdapterValidation;
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
import org.springframework.web.bind.support.SessionStatus;

/**
 * User registration controller
 */
@Controller
public class RegisterFormController {
    
    private final UserService service;
    private final RegisterFormAdapterValidation formValidation;
    
    @Autowired
    public RegisterFormController(UserService service,
                RegisterFormAdapterValidation formValidation) {
        this.service = service;
        this.formValidation = formValidation;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(formValidation);
    }
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model) {  
        RegisterFormAdapter registerForm = new RegisterFormAdapter();
        model.addAttribute("register", registerForm);
		return "register_form";
	}
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerSubmit(
            @ModelAttribute("register") @Validated RegisterFormAdapter registerForm,
            BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "register_form";
        }
        
        service.register(registerForm.getUsername(), registerForm.getEmail(), 
                registerForm.getPassword());
		return "register_form";
	}
    
}
