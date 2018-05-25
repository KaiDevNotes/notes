package root.application;

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
        Ticket ticket = ticketGateway.findById(request.getTicketId());
        if (ticket == null)
        {
            throw new UseCaseExecutionException();
        }
        request.setTicket(ticket);
    }
}
