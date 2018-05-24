package root.application;

import root.domain.Ticket;
import root.domain.User;

public interface TicketFactory 
{
    Ticket create(String issueDescription, User submitter);
}
