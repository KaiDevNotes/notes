package root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import root.application.SaveUserUseCaseImpl;
import root.infrastructure.UserGatewayImpl;
import root.application.DeleteUserUseCase;
import root.application.DeleteUserUseCaseImpl;
import root.application.UserGateway;
import root.application.SaveUserUseCase;

@Configuration
public class ApplicationConfiguration 
{
    @Bean
    public SaveUserUseCase saveUserUseCase()
    {
        return new SaveUserUseCaseImpl(userGateway());
    }
    
    @Bean
    public DeleteUserUseCase deleteUserUseCase()
    {
        return new DeleteUserUseCaseImpl(userGateway());
    }
    
    @Bean
    public UserGateway userGateway()
    {
        return new UserGatewayImpl();
    }
}
