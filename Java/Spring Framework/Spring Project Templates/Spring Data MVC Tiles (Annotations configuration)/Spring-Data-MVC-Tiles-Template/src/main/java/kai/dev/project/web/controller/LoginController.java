package kai.dev.project.web.controller;

import javax.validation.Valid;
import kai.dev.project.dao.UserDAO;
import kai.dev.project.entity.User;
import kai.dev.project.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
    
    @Autowired
    private UserDAO userDAO;
    
    @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
    public String redirectToLoginPage(Model model) 
    {         
        return "redirect:/login";
    } 
    
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String viewLoginPage(Model model) 
    {         
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }   
    
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String doLogin(@Valid LoginForm loginForm, 
                          BindingResult bindingResult, 
                          RedirectAttributes redirectAttributes,
                          Model model)
    {     
        String login = loginForm.getLogin();
        String password = loginForm.getPassword();
        
        if (bindingResult.hasErrors() || 
            login.equals("") ||
            password.equals("")) 
        {
            model.addAttribute("errorMessage", "Not all fields are filled");
            return "login";
        }
        
        User user = userDAO.getByLoginAndPassword(login, password);
        if (user == null)
        {
            model.addAttribute("errorMessage", "Incorret login and/or password");
            return "login";
        }        
        
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/hello";
    }
}
