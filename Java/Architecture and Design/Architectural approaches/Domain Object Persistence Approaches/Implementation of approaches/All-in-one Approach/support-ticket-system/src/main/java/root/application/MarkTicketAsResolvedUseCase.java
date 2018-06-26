package root.application;

import java.util.UUID;
import root.domain.Ticket;

public class MarkTicketAsResolvedUseCase implements UseCase<MarkTicketAsResolvedRequest>  
{    
    private final TicketGateway ticketGateway;

    public MarkTicketAsResolvedUseCase(TicketGateway ticketGateway) 
    {
        this.ticketGateway = ticketGateway;
    }
    
    @Override
    public void execute(MarkTicketAsResolvedRequest request, UseCaseResponse response)
    {
        Ticket ticket = ticketGateway.findById(UUID.fromString(request.getTicketId()));
        if (ticket == null)
        {
            response.markAsFailed("Incorrect Ticket ID");
            return;
        }
        
        ticket.markAsResolved();
        ticketGateway.save(ticket);
        
        response.markAsSuccessful(ticket);
    }
}
