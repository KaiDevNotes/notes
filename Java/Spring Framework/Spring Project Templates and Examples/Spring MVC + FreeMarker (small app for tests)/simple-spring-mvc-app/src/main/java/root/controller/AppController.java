package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import root.dao.UserDAO;

@Controller
public class AppController 
{
    @Autowired
    private UserDAO userDAO;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String openApp()
    {
        return "redirect:/users";
    }
    
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public String showUsers(Model model)
    {
        model.addAttribute("users", userDAO.getAll());
        return "users";
    }
}
