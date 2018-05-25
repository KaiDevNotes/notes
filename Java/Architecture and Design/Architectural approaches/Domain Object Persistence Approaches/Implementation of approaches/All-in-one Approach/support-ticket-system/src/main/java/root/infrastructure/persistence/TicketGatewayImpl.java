package root.infrastructure.persistence;

import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import root.application.TicketGateway;
import root.domain.Ticket;

@Component
public class TicketGatewayImpl extends AbstractGateway<Ticket> implements TicketGateway
{
    @Resource
    private TicketRepository ticketRepository;
    
    @Override
    protected JpaRepository getRepository()
    {
        return ticketRepository;
    }
}
