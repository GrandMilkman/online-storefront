package file.mvc;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import file.entity.Ware;
import file.service.impl.WareServiceImpl;

@Controller
public class ShopController {

    @Autowired
    WareServiceImpl u;

    @RequestMapping(value = "shop", method = RequestMethod.GET)
    public String Shop(Model model) {
        List<Ware> ware = u.getAll();
        model.addAttribute("wares", ware);
        return "shop";
    }
}
