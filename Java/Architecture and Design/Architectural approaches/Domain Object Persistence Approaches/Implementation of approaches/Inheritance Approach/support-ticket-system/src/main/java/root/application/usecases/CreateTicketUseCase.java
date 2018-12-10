package root.application.usecases;

import java.util.UUID;

import root.application.TicketFactory;
import root.application.TicketGateway;
import root.application.UseCase;
import root.application.UseCaseResponse;
import root.application.UserGateway;
import root.domain.Ticket;
import root.domain.User;

public class CreateTicketUseCase implements UseCase<CreateTicketRequest>
{
    private final UserGateway userGateway;
    private final TicketGateway ticketGateway;
    private final TicketFactory ticketFactory;

    public CreateTicketUseCase(
        final UserGateway userGateway, final TicketGateway ticketGateway, final TicketFactory ticketFactory) 
    {
        this.userGateway = userGateway;
        this.ticketGateway = ticketGateway;
        this.ticketFactory = ticketFactory;
    }
    
    @Override
    public void execute(final CreateTicketRequest request, final UseCaseResponse response)
    {
        final User submitter = userGateway.findById(UUID.fromString(request.getSubmitterId()));
        if (submitter == null)
        {
            response.markAsFailed("Incorrect Submitter ID");
            return;
        }
        
        final Ticket ticket = ticketGateway.save(
            ticketFactory.create(request.getIssueDescription(), submitter));
        
        response.markAsSuccessful(ticket);
    }
}
