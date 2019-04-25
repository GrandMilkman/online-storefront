package file.mvc;

import java.lang.Override;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import file.entity.User;

public class SignUpValidator implements Validator {

  
	@Override
	public boolean supports(Class c) {
		return User.class.isAssignableFrom(c); // == instansof
	}

	@Override
	public void validate(Object target, Errors errors) {
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "required.mail");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password");
	}


}
