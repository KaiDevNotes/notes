package root.presentation;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import root.application.SecurityService;
import root.application.UseCaseExecutionException;
import root.application.user.RegisterUserRequest;
import root.application.user.RegisterUserUseCase;

@Controller
public class ApplicationController 
{
    private static final String USER = "user";    
    private static final String SUCCESS_MESSAGE = "successMessage";
    private static final String FAILURE_MESSAGE = "failureMessage";
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired 
    private RegisterUserUseCase registerUserUseCase;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String openHomePage()
    {
        return "home";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String openLoginPage()
    {
        return "login";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.GET)
    public String openRegistrationPage()
    {
        return "registration";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(
        @Valid RegisterUserRequest request, RedirectAttributes model)
    {
        try 
        {
            registerUserUseCase.execute(request);
            securityService.login(request.getLogin(), request.getPassword());
            model.addFlashAttribute(SUCCESS_MESSAGE, 
                "You have been register successfully");
            return "redirect:/user/details";
        }
        catch (UseCaseExecutionException e)
        {
            model.addFlashAttribute(FAILURE_MESSAGE, e.getMessage());
            return "redirect:/registration";
        }        
    }
    
    @RequestMapping(
        value={
            "/user/details", 
            "/admin/details"
        }, 
        method=RequestMethod.GET)
    public String openDetailsPage(Model model)
    {
        model.addAttribute(USER, securityService.getLoggedInUser());
        return "details";
    }
    
    @RequestMapping(value="/403", method=RequestMethod.GET)
    public String openAccessDeniedPage()
    {
        return "403";
    }
}
