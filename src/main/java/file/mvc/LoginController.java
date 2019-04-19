package file.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import file.entity.User;

@Controller
public class LoginController {
	@RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user,Model model) {
		model.addAttribute("userName", user.getName());
        return "/login";
    }
}
