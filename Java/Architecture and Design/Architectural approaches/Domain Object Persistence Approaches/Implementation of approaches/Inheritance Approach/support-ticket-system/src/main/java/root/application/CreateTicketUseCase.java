package root.application;

public class CreateTicketUseCase 
{
    private final CreateTicketRequestTransformer requestTransformer;
    private final TicketFactory ticketFactory;
    private final TicketGateway ticketGateway;

    public CreateTicketUseCase(
        CreateTicketRequestTransformer requestTransformer, 
        TicketFactory ticketFactory, TicketGateway ticketGateway) 
    {
        this.requestTransformer = requestTransformer;
        this.ticketFactory = ticketFactory;
        this.ticketGateway = ticketGateway;
    }
    
    public void execute(CreateTicketRequest request)
    {
        requestTransformer.transform(request);
        
        ticketGateway.save(
            ticketFactory.create(
                request.getIssueDescription(), request.getSubmitter()));
    }
}
