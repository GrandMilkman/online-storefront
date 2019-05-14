package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String start_index() {
        return "index";
    }
}
