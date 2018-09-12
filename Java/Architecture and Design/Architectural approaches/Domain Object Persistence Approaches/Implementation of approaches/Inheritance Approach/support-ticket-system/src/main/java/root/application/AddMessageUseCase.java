package root.application;

import java.util.UUID;
import root.domain.Ticket;
import root.domain.User;

public class AddMessageUseCase implements UseCase<AddMessageRequest> 
{
    private final UserGateway userGateway;
    private final TicketGateway ticketGateway;

    public AddMessageUseCase(UserGateway userGateway, TicketGateway ticketGateway) 
    {
        this.userGateway = userGateway;
        this.ticketGateway = ticketGateway;
    }
    
    @Override
    public void execute(AddMessageRequest request, UseCaseResponse response)
    {
        Ticket ticket = ticketGateway.findById(UUID.fromString(request.getTicketId()));
        if (ticket == null)
        {
            response.markAsFailed("Incorrect Ticket ID");
            return;
        }
        
        User sender = userGateway.findById(UUID.fromString(request.getSenderId()));
        if (sender == null)
        {
            response.markAsFailed("Incorrect Sender ID");
            return;
        }
        
        ticket.addMessage(request.getMessageText(), sender);
        ticketGateway.save(ticket);
        
        response.markAsSuccessful(ticket);
    }
}