package root.application;

import java.util.UUID;
import root.domain.Ticket;

public class MarkTicketAsResolvedRequestTransformer 
{
    private final TicketGateway ticketGateway;

    public MarkTicketAsResolvedRequestTransformer(TicketGateway ticketGateway) 
    {
        this.ticketGateway = ticketGateway;
    }
    
    public void transform(MarkTicketAsResolvedRequest request)
    {
        if (request.hasErrors())
        {
            throw new UseCaseExecutionException();
        }
        Ticket ticket = ticketGateway.findById(
            UUID.fromString(request.getTicketId()));
        if (ticket == null)
        {
            throw new UseCaseExecutionException();
        }
        request.setTicket(ticket);
    }
}
