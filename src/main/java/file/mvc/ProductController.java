package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class ProductController {
	@RequestMapping(value = "product", method = RequestMethod.GET)
    public String product() {
        return "product";
    }
}
