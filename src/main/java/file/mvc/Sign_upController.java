package file.mvc;

import java.io.FileWriter;
import java.io.IOException;

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
import file.mail.MailSender;
import file.service.UserService;


@Controller
public class Sign_upController {
	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("sign_up");
		mav.addObject("userJSP", new User());
		return mav;
	}

	@Autowired
	public UserService us;

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView submit( @ModelAttribute("userJSP") User user,BindingResult result) throws IOException {

		SignUpValidator validator = new SignUpValidator();
		validator.validate(user, result);
		if (result.hasErrors()) {
			return new ModelAndView("sign_up");
		}
		user.setConfirm("false");
		(new MailSender()).sendMailTo(user);
		us.addUser(user);
	    
		
		
		
		return new ModelAndView("login");

	}
}
