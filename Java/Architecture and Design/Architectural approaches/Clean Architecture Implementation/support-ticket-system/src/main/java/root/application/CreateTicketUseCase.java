package root.application;

import root.domain.Ticket;
import root.domain.User;

public class CreateTicketUseCase implements UseCase<CreateTicketRequest>
{
    private final UserGateway userGateway;
    private final TicketGateway ticketGateway;

    public CreateTicketUseCase(final UserGateway userGateway, final TicketGateway ticketGateway) 
    {
        this.userGateway = userGateway;
        this.ticketGateway = ticketGateway;
    }
    
    @Override
    public void execute(final CreateTicketRequest request, final UseCaseResponse response)
    {
        final User submitter = userGateway.findById(request.getSubmitterId());
        if (submitter == null)
        {
            response.markAsFailed("Incorrect Submitter ID");
            return;
        }
        
        final Ticket ticket = ticketGateway.save(
            new Ticket.Builder(request.getIssueDescription(), submitter).build());
        
        response.markAsSuccessful(ticket);
    }
}
