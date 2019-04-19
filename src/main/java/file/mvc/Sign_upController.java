package file.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import file.entity.User;
import file.service.UserService;

@Controller
public class Sign_upController {
	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("sign_up");
		mav.addObject("user", new User());
		return mav;
	}

	@Autowired
	public UserService us;

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
	@ModelAttribute("user") User user) {

			us.addUser(user);
			return new ModelAndView("login", "userName", user.getName());}
		

	}
