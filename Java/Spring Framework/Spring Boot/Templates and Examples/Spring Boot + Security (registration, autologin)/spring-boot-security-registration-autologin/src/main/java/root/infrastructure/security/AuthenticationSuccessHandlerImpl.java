package root.infrastructure.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import root.application.SecurityService;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler
{
    @Autowired
    private SecurityService securityService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException 
    {        
        response.setStatus(HttpServletResponse.SC_OK);

        switch (securityService.getLoggedInUser().getRole()) 
        {
            case USER:
                response.sendRedirect("/user/details");
                break;
            case ADMIN:
                response.sendRedirect("/admin/details");
                break;
            default:
                response.sendRedirect("/login");
                break;
        }
    }
}
