package root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import root.application.SecurityService;
import root.application.UserGateway;
import root.application.user.RegisterUserUseCase;
import root.infrastructure.persistence.UserGatewayImpl;
import root.infrastructure.security.SecurityServiceImpl;

@Configuration
public class ApplicationConfiguration 
{
    @Bean
    public UserGateway userGateway()
    {
        return new UserGatewayImpl();
    }
    
    @Bean
    public SecurityService securityService()
    {
        return new SecurityServiceImpl();
    }
    
    @Bean
    public RegisterUserUseCase registerUserUseCase()
    {
        return new RegisterUserUseCase(userGateway());
    }
}
