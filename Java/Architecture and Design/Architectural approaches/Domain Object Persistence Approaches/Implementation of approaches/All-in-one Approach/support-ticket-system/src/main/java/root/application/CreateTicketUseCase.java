package root.application;

import java.util.UUID;
import root.domain.Ticket;
import root.domain.User;

public class CreateTicketUseCase implements UseCase<CreateTicketRequest>
{
    private final UserGateway userGateway;
    private final TicketGateway ticketGateway;

    public CreateTicketUseCase(UserGateway userGateway, TicketGateway ticketGateway) 
    {
        this.userGateway = userGateway;
        this.ticketGateway = ticketGateway;
    }
    
    @Override
    public void execute(CreateTicketRequest request, UseCaseResponse response)
    {
        User submitter = userGateway.findById(UUID.fromString(request.getSubmitterId()));
        if (submitter == null)
        {
            response.markAsFailed("Incorrect Submitter ID");
            return;
        }
        
        Ticket ticket = ticketGateway.save(
            new Ticket(request.getIssueDescription(), submitter));
        
        response.markAsSuccessful(ticket);
    }
}
