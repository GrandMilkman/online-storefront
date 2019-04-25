package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
public class ShopController {
	@RequestMapping(value = "shop", method = RequestMethod.GET)
    public String shop() {
        return "/shop";
    }
}
