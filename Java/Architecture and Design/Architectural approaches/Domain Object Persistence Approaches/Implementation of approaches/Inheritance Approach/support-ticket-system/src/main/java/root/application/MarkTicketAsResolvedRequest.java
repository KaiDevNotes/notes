package root.application;

import static root.application.ValidationHelper.isValidDomainObjectId;
import root.domain.Ticket;

public class MarkTicketAsResolvedRequest 
{
    private String ticketId;
    private Ticket ticket;
    
    public boolean hasErrors()
    {
        return !isValidDomainObjectId(ticketId);
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) 
    {
        this.ticketId = ticketId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) 
    {
        this.ticket = ticket;
    }    
}
