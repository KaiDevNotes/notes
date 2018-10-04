package root.infrastructure.persistence;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

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
    protected TicketRepository getRepository()
    {
        return ticketRepository;
    }
    
    @Override
    protected TicketAndDbEntryMapper getMapper()
    {
        return mapper;
    }
}
