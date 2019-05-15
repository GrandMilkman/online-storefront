package file.mvc;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import file.entity.Ware;
import file.service.impl.CartServiceImpl;
import file.service.impl.WareServiceImpl;

@Controller
public class ShopController {

    final String WARE_CONST = "ware";

    @Autowired
    WareServiceImpl wareService;

    @Autowired
    CartServiceImpl cartService;

    @RequestMapping(value = "shop", method = RequestMethod.GET)
    public String Shop(Model model) {
        List<Ware> ware = wareService.getAll();
        model.addAttribute("wares", ware);
        return "shop";
    }

    @RequestMapping(value = "/addToCart{wareId}")
    public String addToCart(@PathVariable("wareId") Long wareId, HttpServletRequest request,
            HttpServletResponse response) {
        boolean foundFlag = false;
        Ware w = wareService.getWare(wareId);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().substring(0, 4).equals(WARE_CONST)
                        && c.getName().substring(4).equals(w.getId().toString())) {
                    foundFlag = true;
                    break;
                }
            }
        }
        if (!foundFlag) {
            String newCookieName = WARE_CONST + w.getId().toString();
            Cookie newCookie = new Cookie(newCookieName, null);
            newCookie.setMaxAge(3600 * 24 * 7);
            response.addCookie(newCookie);
        }
        return "redirect:/shop";
    }

}
