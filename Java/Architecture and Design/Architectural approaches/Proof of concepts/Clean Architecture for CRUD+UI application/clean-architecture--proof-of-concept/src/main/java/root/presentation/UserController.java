package root.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import root.application.UserForm;
import root.application.DeleteUserUseCase;
import root.application.UserGateway;
import root.application.SaveUserUseCase;
import root.domain.User;

@Controller
public class UserController 
{    
    @Autowired
    private SaveUserUseCase saveUserUseCase;
    
    @Autowired
    private DeleteUserUseCase deleteUserUseCase;
    
    @Autowired
    private UserGateway userGateway;
    
    @RequestMapping(value={"/", "/users"}, method=RequestMethod.GET)
    public String showUsers(Model model)
    {
        model.addAttribute("users", userGateway.findAll());
        return "users";
    }
    
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String add(UserForm userForm, Model model)
    {
        UsersPresenterImpl presenter = new UsersPresenterImpl(model, userGateway);
        saveUserUseCase.execute(userForm, presenter);        
        return presenter.getViewName();
    }
    
    @RequestMapping(value="/users/new", method=RequestMethod.GET)
    public String showNewUserView(Model model)
    {
        model.addAttribute("form", new UserForm());
        return "user-form";
    }
    
    @RequestMapping(value="/users/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable Integer id, Model model)
    {
        UsersPresenterImpl presenter = new UsersPresenterImpl(model, userGateway);
        deleteUserUseCase.execute(id, presenter);        
        return presenter.getViewName();
    }
    
    @RequestMapping(value="/users/{id}/modify", method=RequestMethod.GET)
    public String showModifyUserView(@PathVariable Integer id, Model model)
    {
        User user = userGateway.findById(id);
        model.addAttribute("form", new UserForm(user));
        return "user-form";
    }
}