package root.infrastructure.persistence;

import org.springframework.stereotype.Component;

import root.application.TicketFactory;
import root.domain.Ticket;
import root.domain.User;

@Component
public class TicketFactoryImpl implements TicketFactory
{
    @Override
    public Ticket create(String issueDescription, User submitter)
    {
        return new TicketDbEntry(issueDescription, submitter);
    }
}
