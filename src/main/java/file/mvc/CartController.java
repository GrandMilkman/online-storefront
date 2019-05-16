package file.mvc;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import file.entity.Ware;
import file.service.impl.UserServiceImpl;
import file.service.impl.WareServiceImpl;

@Controller
public class CartController {

    final String WARE_CONST = "ware";

    @Autowired
    WareServiceImpl wareService;
    
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String cart(Model model, HttpServletRequest request, HttpServletResponse response) {
        List<Ware> wareList = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().substring(0, 4).equals(WARE_CONST)) {
                    Ware w = wareService.getWare(Long.parseLong(c.getName().substring(4)));
                    wareList.add(w);
                }
            }
        }
        model.addAttribute("wares", wareList);
        double totalPrice = 0;
        if (wareList.size() != 0) {
            for (Ware w : wareList) {
                totalPrice += w.getPrice();
            }
        }
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @RequestMapping(value = "/deleteWareFromCart{wareId}")
    public String deleteWareFromCart(@PathVariable("wareId") Long wareId, HttpServletRequest request,
            HttpServletResponse response) {
        Ware w = wareService.getWare(wareId);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().substring(0, 4).equals(WARE_CONST)
                        && c.getName().substring(4).equals(w.getId().toString())) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
        }

        return "redirect:/cart";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkOut(HttpServletRequest request, HttpServletResponse response, Principal p) {
        List<Ware> wareList = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().substring(0, 4).equals(WARE_CONST)) {
                    Ware w = wareService.getWare(Long.parseLong(c.getName().substring(4)));
                    wareList.add(w);
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        if(wareList.size() != 0) {
            for(Ware w : wareList) {
                wareService.mapWareToUser(userService.getUser(p.getName()), w);
            }
        }
        
        return "redirect:/cart";
    }
}
