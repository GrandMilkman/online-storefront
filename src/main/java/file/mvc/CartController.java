package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class CartController {
	@RequestMapping(value = "cart", method = RequestMethod.GET)
    public String cart() {
        return "cart";
    }
}
