package root.infrastructure.persistence;

import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.domain.Message;
import root.domain.Ticket;

@Component
public class TicketAndDbEntryMapper
    implements DomainObjectAndDbEntityMapper<Ticket, TicketDbEntry> 
{
    @Autowired 
    private MessageAndDbEntryMapper messageAndDbEntryMapper;
    
    @Autowired 
    private UserAndDbEntityMapper userAndDbEntityMapper;
    
    @Override
    public TicketDbEntry mapToDbEntity(Ticket ticket)
    {
        TicketDbEntry ticketDbEntry = new TicketDbEntry();
        if (ticket.getId() != null)
        {
            ticketDbEntry.setId(UUID.fromString(ticket.getId()));
        } 
        ticketDbEntry.setIssueDescription(ticket.getIssueDescription());
        ticketDbEntry.setMessages(ticket.getMessages().stream()
            .map(message -> createMessageDbEntry(message, ticketDbEntry))
            .collect(Collectors.toList()));
        ticketDbEntry.setCreationDate(ticket.getCreationDate());
        ticketDbEntry.setResolutionDate(ticket.getResolutionDate());
        ticketDbEntry.setStatus(ticket.getStatus());
        ticketDbEntry.setSubmitter(
            userAndDbEntityMapper.mapToDbEntity(ticket.getSubmitter()));
        return ticketDbEntry;
    }
    
    @Override
    public Ticket mapToDomainObject(TicketDbEntry ticketDbEntry)
    {
        return new Ticket.Builder(
                ticketDbEntry.getIssueDescription(), 
                userAndDbEntityMapper.mapToDomainObject(ticketDbEntry.getSubmitter()))
            .id(ticketDbEntry.getId().toString())
            .messages(ticketDbEntry.getMessages().stream()
                .map(messageDbEntity -> messageAndDbEntryMapper.mapToDomainObject(messageDbEntity))
                .collect(Collectors.toList()))
            .creationDate(ticketDbEntry.getCreationDate())
            .resolutionDate(ticketDbEntry.getResolutionDate())
            .status(ticketDbEntry.getStatus())
            .build();
    }
    
    private MessageDbEntry createMessageDbEntry(
            Message message, TicketDbEntry ticketDbEntity)
    {
        MessageDbEntry messageDbEntry = messageAndDbEntryMapper.mapToDbEntity(message);
        messageDbEntry.setTicket(ticketDbEntity);
        return messageDbEntry;
    }
}
