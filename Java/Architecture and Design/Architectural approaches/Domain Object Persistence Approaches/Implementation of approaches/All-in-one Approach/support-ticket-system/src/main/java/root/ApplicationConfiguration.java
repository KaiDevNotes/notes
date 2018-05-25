package root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import root.application.AddMessageToTicketRequestTransformer;
import root.application.AddMessageToTicketUseCase;
import root.application.CreateTicketRequestTransformer;
import root.application.CreateTicketUseCase;
import root.application.MarkTicketAsResolvedRequestTransformer;
import root.application.MarkTicketAsResolvedUseCase;
import root.application.TicketGateway;
import root.application.UserGateway;
import root.infrastructure.persistence.TicketGatewayImpl;
import root.infrastructure.persistence.UserGatewayImpl;

@Configuration
public class ApplicationConfiguration 
{
    @Bean 
    public MarkTicketAsResolvedUseCase markTicketAsResolvedUseCase()
    {
        return new MarkTicketAsResolvedUseCase(
            markTicketAsResolvedRequestTransformer(), ticketGateway());
    }
    
    @Bean 
    public MarkTicketAsResolvedRequestTransformer markTicketAsResolvedRequestTransformer()
    {
        return new MarkTicketAsResolvedRequestTransformer(ticketGateway());
    }
    
    @Bean 
    public CreateTicketUseCase createTicketUseCase()
    {
        return new CreateTicketUseCase(createTicketRequestTransformer(), ticketGateway());
    }
    
    @Bean 
    public CreateTicketRequestTransformer createTicketRequestTransformer()
    {
        return new CreateTicketRequestTransformer(userGateway());
    }
    
    @Bean 
    public AddMessageToTicketUseCase addMessageToTicketUseCase()
    {
        return new AddMessageToTicketUseCase(
            addMessageToTicketRequestTransformer(), ticketGateway());
    }
    
    @Bean 
    public AddMessageToTicketRequestTransformer addMessageToTicketRequestTransformer()
    {
        return new AddMessageToTicketRequestTransformer(ticketGateway(), userGateway());
    }
    
    @Bean
    public UserGateway userGateway()
    {
        return new UserGatewayImpl();
    }
    
    @Bean
    public TicketGateway ticketGateway()
    {
        return new TicketGatewayImpl();
    }
}
