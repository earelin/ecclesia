package org.earelin.ecclesia.web.form.validation;

import org.apache.commons.validator.routines.EmailValidator;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.web.form.RegisterForm;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Register form adapter validation
 * 
 * @author Xavier Carriba
 * @since 0.1
 */
public class RegisterFormValidator implements Validator {
    
    private final EmailValidator emailValidator = EmailValidator.getInstance();
    private final PasswordValidator passwordValidator;
    private final UserService userService;
    
    public RegisterFormValidator(UserService userService,
	    PasswordValidator passwordValidator) {
	this.userService = userService;
	this.passwordValidator = passwordValidator;
    }

    @Override
    public boolean supports(Class<?> type) {
        return RegisterForm.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
	RegisterForm registerForm = (RegisterForm) o;
        
	// Check username
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
	if (userService.isUsernameUsed(registerForm.getUsername())) {
	    errors.rejectValue("username", "username.exists");
	}
	
	// Check email
	if (!emailValidator.isValid(registerForm.getEmail())) {
	    errors.rejectValue("email", "email.not.valid");
	}
	
	// Check password
	RuleResult result = passwordValidator.validate(new PasswordData(registerForm.getPassword()));
	if (!result.isValid()) {
	    for (String msg : passwordValidator.getMessages(result)) {
		errors.rejectValue("password", msg);
	    }
	}
    }

}
