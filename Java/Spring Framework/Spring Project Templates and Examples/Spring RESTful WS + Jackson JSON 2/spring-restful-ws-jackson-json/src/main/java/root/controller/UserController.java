package root.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import root.dto.UserDTO;
import root.dto.UsersDTO;
import root.model.User;

@RestController
public class UserController 
{
    @RequestMapping(value="/")
    public String sendInstructions()
    {
        return "You can make GET call to /api/users in order to get all users. "
                + "Or to /api/users/{id} in order to get particular user";
    }
    
    @RequestMapping(value="/api/users", method=RequestMethod.GET)
    public UsersDTO getAll()
    {
        List<User> users = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> users.add(newUser(i)));
        return new UsersDTO(users);
    }
    
    @RequestMapping(value="/api/users/{id}", method=RequestMethod.GET)
    public UserDTO getById(@PathVariable("id") int id)
    {        
        return new UserDTO(newUser(id));
    }
    
    private User newUser(int i)
    {
        return new User(i, "first-name-" + i, "last-name-" + i, 
                "login-" + i, "password-" + i);
    }
}
