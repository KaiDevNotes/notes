package root.application.usecases;

import root.application.UseCaseRequest;
import root.application.validation.DomainObjectId;

public class MarkTicketAsResolvedRequest implements UseCaseRequest
{
    @DomainObjectId(errorMessage = "Ticket Id is invalid.")
    private String ticketId;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(final String ticketId) 
    {
        this.ticketId = ticketId;
    }   
}
