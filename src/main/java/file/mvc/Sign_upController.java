package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Sign_upController {
	@RequestMapping(value = "sign_up", method = RequestMethod.GET)
    public String sign_up() {
        return "/sign_up";
    }
}
