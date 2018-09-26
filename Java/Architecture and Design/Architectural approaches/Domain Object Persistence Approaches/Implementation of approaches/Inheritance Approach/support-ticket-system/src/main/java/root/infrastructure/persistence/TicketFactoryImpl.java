package root.infrastructure.persistence;

import root.application.TicketFactory;
import root.domain.Ticket;
import root.domain.User;

public class TicketFactoryImpl implements TicketFactory
{
    @Override
    public Ticket create(final String issueDescription, final User submitter)
    {
        return new TicketDbEntry(issueDescription, submitter);
    }
}
