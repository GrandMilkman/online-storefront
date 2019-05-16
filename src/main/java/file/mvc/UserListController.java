package file.mvc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import file.entity.User;
import file.service.impl.UserServiceImpl;
import file.mvc.UserEditValidator;

@Controller
public class UserListController {

    @Autowired
    UserServiceImpl u;

    @RequestMapping(value = "user_list", method = RequestMethod.GET)
    public String user_list(Model model) {
        List<User> user = u.getAll();
        model.addAttribute("users", user);
        return "user_list";
    }

    @RequestMapping("/delete{contactId}")
    public String deleteContact(@PathVariable("contactId") Long contactId) {
        u.deleteUserById(contactId);
        return "redirect:/user_list";
    }

    @RequestMapping(value = "/edit{contactId}", method = RequestMethod.GET)
    public ModelAndView editContact(Model model, @PathVariable("contactId") Long contactId) {
        ModelAndView mav = new ModelAndView("editUser");
        User user = u.getUser(contactId);
        mav.addObject("userJSP", user);
        model.addAttribute("user", user);
        return mav;
    }

    @RequestMapping(value = "/editUserProcess", method = RequestMethod.POST)
    public String editContact(@Valid @ModelAttribute("userJSP") User user, BindingResult result) {
        UserEditValidator validator = new UserEditValidator();
        validator.validate(user, result);
        if (result.hasErrors()) {
            return "editUser";
        }
        User editUser = u.getUser(user.getId());
        editUser.setName(user.getName());
        editUser.setMail(user.getMail());
        editUser.setPassword(user.getPassword());
        u.editUser(editUser);
        return "redirect:/user_list";
    }

}
