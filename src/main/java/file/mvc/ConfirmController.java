package file.mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import file.entity.User;
import file.service.UserService;

@Controller
public class ConfirmController {
	@Autowired
	public UserService us;
	
	@RequestMapping(value="/confirm/{token}", method=RequestMethod.GET)
	public String verificationToken(@PathVariable("token") String token) {
		User u=us.getUserByConfirmToken(token);
		if(u!=null) {
			u.setConfirmToken("true");
			us.editUser(u);
			return "/confirm";
		}
		return "/login";
		
	}
	
	
	
}
