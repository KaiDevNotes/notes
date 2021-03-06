package root.application.usecases;

import root.application.TicketGateway;
import root.application.UseCase;
import root.application.UseCaseResponse;
import root.application.UserGateway;
import root.domain.Ticket;
import root.domain.User;

public class AddMessageUseCase implements UseCase<AddMessageRequest>
{
    private final UserGateway userGateway;
    private final TicketGateway ticketGateway;

    public AddMessageUseCase(final UserGateway userGateway, final TicketGateway ticketGateway) 
    {
        this.userGateway = userGateway;
        this.ticketGateway = ticketGateway;
    }
    
    @Override
    public void execute(final AddMessageRequest request, final UseCaseResponse response)
    {
        final Ticket ticket = ticketGateway.findById(request.getTicketId());
        if (ticket == null)
        {
            response.markAsFailed("Incorrect Ticket ID");
            return;
        }
        
        final User sender = userGateway.findById(request.getSenderId());
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
