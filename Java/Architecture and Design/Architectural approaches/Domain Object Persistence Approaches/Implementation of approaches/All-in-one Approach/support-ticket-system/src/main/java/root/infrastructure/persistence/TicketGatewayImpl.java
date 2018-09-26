package root.infrastructure.persistence;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import root.application.TicketGateway;
import root.domain.Ticket;

public class TicketGatewayImpl extends AbstractDomainObjectGateway<Ticket> implements TicketGateway
{
    @Resource
    private TicketRepository ticketRepository;
    
    @Override
    protected JpaRepository<Ticket, UUID> getRepository()
    {
        return ticketRepository;
    }
}
