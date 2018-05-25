package root.application;

import root.domain.Ticket;

public class CreateTicketUseCase 
{
    private final CreateTicketRequestTransformer requestTransformer;
    private final TicketGateway ticketGateway;

    public CreateTicketUseCase(
        CreateTicketRequestTransformer requestTransformer, 
        TicketGateway ticketGateway) 
    {
        this.requestTransformer = requestTransformer;
        this.ticketGateway = ticketGateway;
    }
    
    public void execute(CreateTicketRequest request)
    {
        requestTransformer.transform(request);
        
        ticketGateway.save(
            new Ticket(request.getIssueDescription(), request.getSubmitter()));
    }
}
