package root.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController 
{
    @Autowired
    private UserDAO userDAO;
    
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public UsersDTO getAll()
    {
        return new UsersDTO(userDAO.getAll());
    }
    
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public UserDTO getUser(@PathVariable int id)
    {
        return new UserDTO(userDAO.getById(id));
    }
}
