package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class MvcController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String start_index() {
        return "index";
    }
}
