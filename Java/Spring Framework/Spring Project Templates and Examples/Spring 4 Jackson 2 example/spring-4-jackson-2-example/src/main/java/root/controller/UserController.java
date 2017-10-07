package root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import root.dto.UsersDTO;
import root.model.User;


@Controller
public class UserController 
{
    @RequestMapping(value="/")
    public String redirect()
    {
        return "redirect:/api/users";
    }
    
    @RequestMapping(value="/api/users", method=RequestMethod.GET)
    public @ResponseBody UsersDTO getAll()
    {
        UsersDTO result = new UsersDTO();
        for (int i = 1; i <= 3; i++)
        {
            result.add(newUser(i));
        }
        return result;
    }
    
    private User newUser(int i)
    {
        return new User(i, "first-name-" + i, "last-name-" + i, 
                        "login-" + i, "password-" + i);
    }
}
