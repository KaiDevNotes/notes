package root.application;

import java.util.UUID;
import root.domain.Ticket;
import root.domain.User;

public class AddMessageToTicketRequestTransformer 
{
    private final TicketGateway ticketGateway;
    private final UserGateway userGateway;

    public AddMessageToTicketRequestTransformer(
        TicketGateway ticketGateway, UserGateway userGateway) 
    {
        this.ticketGateway = ticketGateway;
        this.userGateway = userGateway;
    }
    
    public void transform(AddMessageToTicketRequest request)
    {
        if (request.hasErrors())
        {
            throw new UseCaseExecutionException();
        }
        Ticket ticket = ticketGateway.findById(
            UUID.fromString(request.getTicketId()));
        User sender = userGateway.findById(
            UUID.fromString(request.getSenderId()));
        if (ticket == null || sender == null)
        {
            throw new UseCaseExecutionException();
        }
        request.setTicket(ticket);
        request.setSender(sender);
    }
}
