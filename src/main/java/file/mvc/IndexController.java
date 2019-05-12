package file.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import file.entity.User;
import file.service.UserService;

@Controller
public class IndexController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("/index");
		
		return mav;
	}
}
