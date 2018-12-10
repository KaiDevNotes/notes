package root.application.usecases;

import java.util.UUID;

import root.application.TicketGateway;
import root.application.UseCase;
import root.application.UseCaseResponse;
import root.domain.Ticket;

public class MarkTicketAsResolvedUseCase implements UseCase<MarkTicketAsResolvedRequest>  
{    
    private final TicketGateway ticketGateway;

    public MarkTicketAsResolvedUseCase(final TicketGateway ticketGateway) 
    {
        this.ticketGateway = ticketGateway;
    }
    
    @Override
    public void execute(final MarkTicketAsResolvedRequest request, final UseCaseResponse response)
    {
        final Ticket ticket = ticketGateway.findById(UUID.fromString(request.getTicketId()));
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
