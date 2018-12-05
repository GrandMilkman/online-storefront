package file.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import file.entity.User;
import file.service.UserService;

@Controller
public class UserController {

	private UserValidator userValidator;
    
    private UserService userService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        
        ModelAndView mav = new ModelAndView();
            if(error != null) {
                mav.addObject("error", "Invalid credentials");
            }
            
            mav.setViewName("login");
        return mav;
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("signup");
        return mav;
    }
    
    @RequestMapping(value = "/saveuser")
    public ModelAndView saveUser(@ModelAttribute("signup") User u, BindingResult br) {
        
        ModelAndView mav = new ModelAndView();
        userValidator.validate(u, br);
        
        if(br.hasErrors()) {
            mav.addObject("signup");
            return mav;
        }
        else {
            userService.addUser(u);
            mav.addObject("signup");
            return mav;
        }
    }

    
}
