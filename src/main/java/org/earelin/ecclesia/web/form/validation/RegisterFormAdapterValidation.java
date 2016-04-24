package org.earelin.ecclesia.web.form.validation;

import org.earelin.ecclesia.web.form.RegisterForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Register form adapter validation
 */
@Component
public class RegisterFormAdapterValidation implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return RegisterForm.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
    }

}
