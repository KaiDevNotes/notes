package root;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController 
{
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String viewInstructions()
    {
        return "You can make GET call to ../users in order to get all users. "
             + "Or to ../users/{id} in order to get particular user";
    }
}
