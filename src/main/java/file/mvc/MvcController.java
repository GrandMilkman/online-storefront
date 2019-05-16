package file.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start_index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
}
