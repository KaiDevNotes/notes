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
        User user = userDAO.getById(id);
        if (user == null)
        {
            throw new UserNotFoundException(
                    String.format("User with id [%s] does not exist", id));
        }
        return new UserDTO(user);
    }
}
