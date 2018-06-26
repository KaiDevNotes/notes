package root.infrastructure.persistence;

import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import root.application.TicketGateway;
import root.domain.Ticket;

public class TicketGatewayImpl 
    extends AbstractDomainObjectGateway<Ticket, TicketDbEntry> implements TicketGateway
{
    @Resource
    private TicketRepository ticketRepository;
    
    @Override
    protected JpaRepository getRepository()
    {
        return ticketRepository;
    }
}
