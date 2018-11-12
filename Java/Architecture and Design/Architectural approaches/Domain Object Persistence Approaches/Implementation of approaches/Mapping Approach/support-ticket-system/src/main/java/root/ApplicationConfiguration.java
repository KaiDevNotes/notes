package root;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import root.application.AddMessageRequest;
import root.application.AddMessageUseCase;
import root.application.CreateTicketRequest;
import root.application.CreateTicketUseCase;
import root.application.MarkTicketAsResolvedRequest;
import root.application.MarkTicketAsResolvedUseCase;
import root.application.TicketGateway;
import root.application.UseCase;
import root.application.UseCaseExecutor;
import root.application.UseCaseExecutorImpl;
import root.application.UseCaseRequest;
import root.application.UserGateway;
import root.application.ValidatingUseCaseExecutor;
import root.application.validation.UseCaseRequestValidator;
import root.infrastructure.persistence.TicketGatewayImpl;
import root.infrastructure.persistence.UserGatewayImpl;

@Configuration
public class ApplicationConfiguration
{     
    @Bean
    public UseCaseExecutor getUseCaseExecutor()
    {
    	final Map<Class<? extends UseCaseRequest>, UseCase<? extends UseCaseRequest>> requestToUseCaseMap = new HashMap<>();
        
        requestToUseCaseMap.put(
            CreateTicketRequest.class, 
            new CreateTicketUseCase(getUserGateway(), getTicketGateway()));
        
        requestToUseCaseMap.put(
            AddMessageRequest.class, 
            new AddMessageUseCase(getUserGateway(), getTicketGateway()));
        
        requestToUseCaseMap.put(
            MarkTicketAsResolvedRequest.class, 
            new MarkTicketAsResolvedUseCase(getTicketGateway()));
        
        final UseCaseExecutor executor = new UseCaseExecutorImpl(requestToUseCaseMap); 
        final UseCaseExecutor validatingExecutor = new ValidatingUseCaseExecutor(executor, new UseCaseRequestValidator());
        
        return validatingExecutor;
    }
    
    @Bean
    public UserGateway getUserGateway()
    {
        return new UserGatewayImpl();
    }
    
    @Bean
    public TicketGateway getTicketGateway()
    {
        return new TicketGatewayImpl();
    }
}
