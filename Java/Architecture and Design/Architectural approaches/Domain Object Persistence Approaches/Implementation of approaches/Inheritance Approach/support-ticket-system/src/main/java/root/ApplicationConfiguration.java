package root;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import root.application.AddMessageRequest;
import root.application.AddMessageRequestValidator;
import root.application.AddMessageUseCase;
import root.application.CreateTicketRequest;
import root.application.CreateTicketRequestValidator;
import root.application.CreateTicketUseCase;
import root.application.MarkTicketAsResolvedRequest;
import root.application.MarkTicketAsResolvedRequestValidator;
import root.application.MarkTicketAsResolvedUseCase;
import root.application.TicketFactory;
import root.application.TicketGateway;
import root.application.UseCase;
import root.application.UseCaseExecutor;
import root.application.UserGateway;
import root.infrastructure.persistence.TicketFactoryImpl;
import root.infrastructure.persistence.TicketGatewayImpl;
import root.infrastructure.persistence.UserGatewayImpl;

@Configuration
public class ApplicationConfiguration 
{    
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
    
    @Bean 
    public TicketFactory getTicketFactory()
    {
        return new TicketFactoryImpl();
    }
    
    @Bean
    public UseCaseExecutor getUseCaseExecutor()
    {
        Map<Class<?>, UseCase> requestToUseCaseMap = new HashMap<>();
        
        requestToUseCaseMap.put(CreateTicketRequest.class, getCreateTicketUseCase());
        requestToUseCaseMap.put(AddMessageRequest.class, getAddMessageUseCase());
        requestToUseCaseMap.put(MarkTicketAsResolvedRequest.class, getMarkTicketAsResolvedUseCase());
        
        return new UseCaseExecutor(requestToUseCaseMap);
    }
    
    private UseCase getCreateTicketUseCase()
    {
        UseCase createTicketUseCase = new CreateTicketUseCase(
            getUserGateway(), getTicketGateway(), getTicketFactory());
        
        UseCase requestValidationDecorator = 
            new CreateTicketRequestValidator(createTicketUseCase);
        
        return requestValidationDecorator;
    }
    
    private UseCase getAddMessageUseCase()
    {
        UseCase addMessageUseCase = 
            new AddMessageUseCase(getUserGateway(), getTicketGateway());
        
        UseCase requestValidationDecorator = 
            new AddMessageRequestValidator(addMessageUseCase);
        
        return requestValidationDecorator;
    }
    
    private UseCase getMarkTicketAsResolvedUseCase()
    {
        UseCase markTicketAsResolvedUseCase = 
            new MarkTicketAsResolvedUseCase(getTicketGateway());
        
        UseCase requestValidationDecorator = 
            new MarkTicketAsResolvedRequestValidator(markTicketAsResolvedUseCase);
        
        return requestValidationDecorator;
    }
}
