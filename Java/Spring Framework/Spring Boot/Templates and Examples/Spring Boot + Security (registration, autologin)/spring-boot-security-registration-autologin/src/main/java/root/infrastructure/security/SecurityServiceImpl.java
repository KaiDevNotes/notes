package root.infrastructure.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import root.application.SecurityService;
import root.application.UserGateway;
import root.domain.User;

@RequestScope
@Service
public class SecurityServiceImpl implements SecurityService
{
    @Autowired
    private UserGateway userGateway;
    
    @Autowired
    private HttpServletRequest request;
    
    @Override
    public void login(User user)
    {
        login(user.getLogin(), user.getPassword());
    }
    
    @Override
    public void login(String login, String password)
    {
        try 
        {
            request.login(login, password);
        }
        catch (ServletException e)
        {
            // TODO: Add logging
        }
    }
    
    @Override
    public User getLoggedInUser()
    {
        String login = SecurityContextHolder.getContext()
            .getAuthentication().getName();
        return userGateway.findByLogin(login);
    }
}
