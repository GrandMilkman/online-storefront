package file.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import file.entity.User;
import file.service.impl.UserServiceImpl;


@Controller
public class User_listController {
	
	@Autowired
	UserServiceImpl u;
	
	@RequestMapping(value = "user_list", method = RequestMethod.GET)
    public String user_list(Model model) {
		List<User> user = u.getAll();
		model.addAttribute("users", user);
        return "/user_list";
    }
}
