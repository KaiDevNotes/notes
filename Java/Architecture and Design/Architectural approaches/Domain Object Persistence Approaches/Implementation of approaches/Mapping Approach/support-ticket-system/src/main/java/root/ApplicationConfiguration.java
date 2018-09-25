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
import root.application.TicketGateway;
import root.application.UseCase;
import root.application.UseCaseExecutor;
import root.application.UseCaseRequest;
import root.application.UserGateway;
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
    public UseCaseExecutor getUseCaseExecutor()
    {
    	final Map<Class<? extends UseCaseRequest>, UseCase<? extends UseCaseRequest>> requestToUseCaseMap = new HashMap<>();
        
        requestToUseCaseMap.put(CreateTicketRequest.class, getCreateTicketUseCase());
        requestToUseCaseMap.put(AddMessageRequest.class, getAddMessageUseCase());
        requestToUseCaseMap.put(MarkTicketAsResolvedRequest.class, getMarkTicketAsResolvedUseCase());
        
        return new UseCaseExecutor(requestToUseCaseMap);
    }
    
    private UseCase<CreateTicketRequest> getCreateTicketUseCase()
    {
    	final UseCase<CreateTicketRequest> createTicketUseCase = 
            new CreateTicketUseCase(getUserGateway(), getTicketGateway());
        
    	final UseCase<CreateTicketRequest> requestValidationDecorator = 
            new CreateTicketRequestValidator(createTicketUseCase);
        
        return requestValidationDecorator;
    }
    
    private UseCase<AddMessageRequest> getAddMessageUseCase()
    {
        final UseCase<AddMessageRequest> addMessageUseCase = 
            new AddMessageUseCase(getUserGateway(), getTicketGateway());
        
        final UseCase<AddMessageRequest> requestValidationDecorator = 
            new AddMessageRequestValidator(addMessageUseCase);
        
        return requestValidationDecorator;
    }
    
    private UseCase<MarkTicketAsResolvedRequest> getMarkTicketAsResolvedUseCase()
    {
        final UseCase<MarkTicketAsResolvedRequest> markTicketAsResolvedUseCase = 
            new MarkTicketAsResolvedUseCase(getTicketGateway());
        
        final UseCase<MarkTicketAsResolvedRequest> requestValidationDecorator = 
            new MarkTicketAsResolvedRequestValidator(markTicketAsResolvedUseCase);
        
        return requestValidationDecorator;
    }
}
