package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import file.service.UserService;

@Controller
public class UsersController {

	private UserService userService;

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("USER_LIST", userService.getAll());
		return "users";
	}
}
