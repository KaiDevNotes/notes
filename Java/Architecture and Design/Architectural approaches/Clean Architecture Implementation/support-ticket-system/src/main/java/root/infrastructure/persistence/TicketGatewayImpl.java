package root.infrastructure.persistence;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import root.application.TicketGateway;
import root.domain.Ticket;

public class TicketGatewayImpl
    extends AbstractDomainObjectGateway<Ticket, TicketDbEntry> 
    implements TicketGateway
{
    @Resource
    private TicketRepository ticketRepository;
    
    @Autowired 
    private TicketAndDbEntryMapper mapper;
    
    @Override
    protected JpaRepository<TicketDbEntry, UUID> getRepository()
    {
        return ticketRepository;
    }
    
    @Override
    protected DomainObjectAndDbEntryMapper<Ticket, TicketDbEntry> getMapper()
    {
        return mapper;
    }
}
