package file.mvc;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import file.entity.User;
import file.service.UserService;
import file.mvc.UserEditValidator;

@Controller
public class UserEditController {
    @Autowired
    public UserService us;

    @RequestMapping(value = "user_editing", method = RequestMethod.GET)
    public ModelAndView editUser(Model model,Principal principal) {
        ModelAndView mav = new ModelAndView("user_editing");
        String userMail  = principal.getName();
        User editUser = us.getUser(userMail);
        mav.addObject("userJSP", editUser);
        model.addAttribute("user", editUser);

        return mav;
    }

    @RequestMapping(value = "/editProcess", method = RequestMethod.POST)
    public ModelAndView submitEdit( @ModelAttribute("userJSP") User user,BindingResult result,Principal principal) {
        String userMail  = principal.getName();

        UserEditValidator validator = new UserEditValidator();
        validator.validate(user, result);
        if (result.hasErrors()) {
            return new ModelAndView("user_editing");
        }
        User editUser = us.getUser(userMail);
        editUser.setName(user.getName());
        editUser.setMail(user.getMail());
        editUser.setPassword(user.getPassword());
        us.editUser(editUser);

        return new ModelAndView("login");

    }

}
