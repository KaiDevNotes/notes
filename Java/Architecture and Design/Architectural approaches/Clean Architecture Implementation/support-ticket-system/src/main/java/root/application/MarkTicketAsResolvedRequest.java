package root.application;

public class MarkTicketAsResolvedRequest implements UseCaseRequest  
{
    private String ticketId;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) 
    {
        this.ticketId = ticketId;
    }   
}
