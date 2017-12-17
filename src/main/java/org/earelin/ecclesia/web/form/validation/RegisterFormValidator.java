package org.earelin.ecclesia.web.form.validation;

import org.apache.commons.validator.routines.EmailValidator;
import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.web.form.RegisterForm;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;
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
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
	if (!errors.hasFieldErrors("email") && !emailValidator.isValid(registerForm.getEmail())) {
	    errors.rejectValue("email", "email.not.valid");
	}
	
	// Check password
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
	if (!errors.hasFieldErrors("password")) {
	    RuleResult result = passwordValidator.validate(new PasswordData(registerForm.getPassword()));
	    if (!result.isValid()) {
		for (RuleResultDetail resultDetail : result.getDetails()) {
		    errors.rejectValue("password", resultDetail.getErrorCode());
		}
	    }
	}
    }

}
