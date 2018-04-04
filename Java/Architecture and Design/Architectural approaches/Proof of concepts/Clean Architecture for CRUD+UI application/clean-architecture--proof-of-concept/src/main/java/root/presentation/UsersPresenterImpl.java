package root.presentation;

import root.application.UsersPresenter;
import org.springframework.ui.Model;
import root.application.Message;
import root.application.UserForm;
import root.application.UserGateway;

public class UsersPresenterImpl implements UsersPresenter 
{
    private String viewName;
    private final Model model;
    private final UserGateway userGateway;
    
    public UsersPresenterImpl(Model model, UserGateway userGateway)
    {
        this.model = model;
        this.userGateway = userGateway;
    }

    public String getViewName() 
    {
        return viewName;
    }
    
    @Override
    public void showUsersView(Message message)
    {
        viewName = "users";
        model.addAttribute("users", userGateway.findAll());
        model.addAttribute("message", message);
    }
    
    @Override
    public void showFormWithError(UserForm form, Message message)
    {
        viewName = "user-form";
        model.addAttribute("form", form);
        model.addAttribute("message", message);
    }
}
