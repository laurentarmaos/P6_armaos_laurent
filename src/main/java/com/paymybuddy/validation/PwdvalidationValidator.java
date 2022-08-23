package com.paymybuddy.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PwdvalidationValidator implements ConstraintValidator<Pwdvalidation, String>{
	
	@Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

	String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$";
	Pattern p = Pattern.compile(regex);

	Matcher m = p.matcher(value);

	return m.matches();

    }

}
