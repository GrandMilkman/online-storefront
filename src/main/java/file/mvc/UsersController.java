package file.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class UsersController {
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users() {
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user_list");
        return mav;
        
    }
}
