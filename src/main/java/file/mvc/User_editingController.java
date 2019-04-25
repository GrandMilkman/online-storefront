package file.mvc;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import file.entity.User;
import file.service.UserService;
import file.mvc.SignUpValidator;

@Controller
public class User_editingController {
	@RequestMapping(value = "user_editing", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("user_editing");
		mav.addObject("userJSP", new User());
		return mav;
	}

	@Autowired
	public UserService us;

	@RequestMapping(value = "/editProcess", method = RequestMethod.POST)
	public ModelAndView submitEdit( @ModelAttribute("userJSP") User user,BindingResult result,Principal principal) {
		String userMail  = principal.getName();

		SignUpValidator validator = new SignUpValidator();
		validator.validate(user, result);
		if (result.hasErrors()) {
			return new ModelAndView("user_editing");
		}
		User newUser = us.getUser(userMail);
		newUser.setName(user.getName());
		newUser.setMail(user.getMail());
		newUser.setPassword(user.getPassword());
		us.editUser(newUser);

		return new ModelAndView("login");

	}

}
