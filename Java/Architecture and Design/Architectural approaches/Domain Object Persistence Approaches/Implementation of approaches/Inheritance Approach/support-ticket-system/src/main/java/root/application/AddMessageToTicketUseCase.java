package root.application;

import root.domain.Ticket;

public class AddMessageToTicketUseCase 
{
    private final AddMessageToTicketRequestTransformer requestTransformer;
    private final TicketGateway ticketGateway;

    public AddMessageToTicketUseCase(
        AddMessageToTicketRequestTransformer requestTransformer, 
        TicketGateway ticketGateway) 
    {
        this.requestTransformer = requestTransformer;
        this.ticketGateway = ticketGateway;
    }
    
    public void execute(AddMessageToTicketRequest request)
    {
        requestTransformer.transform(request);
        
        Ticket ticket = request.getTicket();
        ticket.addMessage(request.getMessageText(), request.getSender());
        ticketGateway.save(ticket);
    }
}
