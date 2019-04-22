package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class User_editingController {
	@RequestMapping(value = "user_editing", method = RequestMethod.GET)
    public String user_editing() {
        return "/user_editing";
    }
}
