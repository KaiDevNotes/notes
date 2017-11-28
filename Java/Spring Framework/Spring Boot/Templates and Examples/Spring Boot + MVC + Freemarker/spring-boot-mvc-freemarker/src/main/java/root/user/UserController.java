package root.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController 
{
    @Autowired
    private UserDAO userDAO;
    
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public String showUsers(Model model)
    {
        model.addAttribute("users", userDAO.getAll());
        return "users";
    }
}
