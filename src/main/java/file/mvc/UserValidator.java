package file.mvc;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import file.entity.User;
import file.service.UserService;


@Component
public class UserValidator implements Validator {

	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;
		validateName(user, errors);
		validatePassword(user, errors);
	}

	private void validateName(User u, Errors e) {

		if (u.getId() == null) {
			if (u.getName() == null || u.getName().equals("")) {
				e.rejectValue("user_name", "isEmpty");
			}

			if (userService.getUser(u.getName()).getName() != null) {
				e.rejectValue("user_name", "nameExist");
			}
		} else {
			User userFound = userService.getUser(u.getName());
			if (userFound.getName() != null && userFound.getId() != u.getId()) {
				e.rejectValue("user_name", "nameExist");
			}
		}
	}

	private void validatePassword(User u, Errors e) {

		if (u.getId() == null) {
			if (u.getPassword() == null || u.getPassword().equals("")) {
				e.rejectValue("user_password", "isEmpty");
			}
		}
	}

}
