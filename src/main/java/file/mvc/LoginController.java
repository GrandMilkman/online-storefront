package file.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.SessionAttributes;


import file.entity.User;

@Controller
@SessionAttributes("userJSP")
public class LoginController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,Model model) {
		ModelAndView m = new ModelAndView();
		m.addObject("userJSP",new User());
		m.setViewName("login");
		return m;
	}

	@RequestMapping(value = "/storefront/login", method = RequestMethod.POST)
	public ModelAndView submit( @ModelAttribute("userJSP") User user) {

		ModelAndView m = new ModelAndView();
		m.setViewName("index");
		m.addObject("userJSP", user);

		return m;
	}
}
