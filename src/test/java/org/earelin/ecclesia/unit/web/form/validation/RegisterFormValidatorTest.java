/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.earelin.ecclesia.unit.web.form.validation;

import org.earelin.ecclesia.service.UserService;
import org.earelin.ecclesia.web.form.RegisterForm;
import org.earelin.ecclesia.web.form.validation.RegisterFormValidator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.passay.PasswordValidator;

/**
 * Register form validator test
 * 
 * @author Xavier Carriba
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterFormValidatorTest {
    
    @Mock
    private UserService userService;
    
    @Mock
    private PasswordValidator passwordValidator;
    
    private RegisterFormValidator validator;
    
    @Before
    public void init() {	
	validator = new RegisterFormValidator(userService, passwordValidator);
    }

    @Test
    public void shouldValidateRegisterForm() {
	assertTrue(validator.supports(RegisterForm.class));
	assertFalse(validator.supports(Object.class));
    }
    
}
