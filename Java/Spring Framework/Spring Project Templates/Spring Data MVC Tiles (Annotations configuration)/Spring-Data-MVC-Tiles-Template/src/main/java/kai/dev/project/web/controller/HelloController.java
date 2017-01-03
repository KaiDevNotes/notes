package kai.dev.project.web.controller;

import kai.dev.project.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloController {

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String viewHelloPage(@ModelAttribute("user") User user, Model model) 
    {         
        model.addAttribute("user", user);
        return "hello";
    } 
    
}
