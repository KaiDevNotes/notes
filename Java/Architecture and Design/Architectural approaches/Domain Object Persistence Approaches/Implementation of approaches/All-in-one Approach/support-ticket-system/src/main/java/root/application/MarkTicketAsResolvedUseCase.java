package root.application;

import root.domain.Ticket;

public class MarkTicketAsResolvedUseCase 
{    
    private final MarkTicketAsResolvedRequestTransformer requestTransformer;
    private final TicketGateway ticketGateway;

    public MarkTicketAsResolvedUseCase(
        MarkTicketAsResolvedRequestTransformer requestTransformer, 
        TicketGateway ticketGateway) 
    {
        this.requestTransformer = requestTransformer;
        this.ticketGateway = ticketGateway;
    }
    
    public void execute(MarkTicketAsResolvedRequest request)
    {
        requestTransformer.transform(request);
        
        Ticket ticket = request.getTicket();
        ticket.markAsResolved();
        ticketGateway.save(ticket);
    }
}
