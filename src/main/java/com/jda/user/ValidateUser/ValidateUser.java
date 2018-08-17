package com.jda.user.ValidateUser;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidateUser implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object user, Errors result) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(result, "email", "required.email", "email is required");
		ValidationUtils.rejectIfEmpty(result, "password", "required.password", "password is required");
		ValidationUtils.rejectIfEmpty(result, "name", "required.name", "name is required");
		ValidationUtils.rejectIfEmpty(result, "mobile", "required.mobile", "mobile is required");
	}

}
