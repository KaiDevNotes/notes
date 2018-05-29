package root.infrastructure.persistence;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import root.application.TicketGateway;
import root.domain.Ticket;

@Component
public class TicketGatewayImpl 
    extends AbstractDomainObjectGateway<Ticket, TicketDbEntry> 
    implements TicketGateway
{
    @Resource
    private TicketRepository ticketRepository;
    
    @Autowired 
    private TicketAndDbEntryMapper mapper;
    
    @Override
    protected JpaRepository getRepository()
    {
        return ticketRepository;
    }
    
    @Override
    protected DomainObjectAndDbEntryMapper getMapper()
    {
        return mapper;
    }
}
