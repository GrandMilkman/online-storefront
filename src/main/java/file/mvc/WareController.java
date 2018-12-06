package file.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import file.service.GroupService;
import file.service.WareService;

@Controller
public class WareController {
    
    private WareService wareService;
    
    private GroupService groupService;
    
    @RequestMapping(value = "/store/{id:\\d+}")
    public ModelAndView ware(@PathVariable("id") Long id) {
        
        ModelAndView mav = new ModelAndView();
        
        mav.addObject(wareService.getWare(id));
        mav.addObject(groupService.getAll());
        
        return mav;
    }
}
