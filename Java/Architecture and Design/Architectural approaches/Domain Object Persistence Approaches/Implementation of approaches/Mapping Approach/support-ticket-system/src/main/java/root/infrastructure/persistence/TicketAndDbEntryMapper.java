package root.infrastructure.persistence;

import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.domain.Message;
import root.domain.Ticket;

@Component
public class TicketAndDbEntryMapper
    implements DomainObjectAndDbEntryMapper<Ticket, TicketDbEntry> 
{
    @Autowired 
    private MessageAndDbEntryMapper messageAndDbEntryMapper;
    
    @Autowired 
    private UserAndDbEntryMapper userAndDbEntryMapper;
    
    @Override
    public TicketDbEntry mapToDbEntry(Ticket ticket)
    {      
        if (ticket == null)
        {
            return null;
        }
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
            userAndDbEntryMapper.mapToDbEntry(ticket.getSubmitter()));
        return ticketDbEntry;
    }
    
    @Override
    public Ticket mapToDomainObject(TicketDbEntry ticketDbEntry)
    {              
        if (ticketDbEntry == null)
        {
            return null;
        }
        return new Ticket.Builder(
                ticketDbEntry.getIssueDescription(), 
                userAndDbEntryMapper.mapToDomainObject(ticketDbEntry.getSubmitter()))
            .id(ticketDbEntry.getId().toString())
            .messages(ticketDbEntry.getMessages().stream()
                .map(messageDbEntry -> messageAndDbEntryMapper.mapToDomainObject(messageDbEntry))
                .collect(Collectors.toList()))
            .creationDate(ticketDbEntry.getCreationDate())
            .resolutionDate(ticketDbEntry.getResolutionDate())
            .status(ticketDbEntry.getStatus())
            .build();
    }
    
    private MessageDbEntry createMessageDbEntry(
            Message message, TicketDbEntry ticketDbEntry)
    {
        MessageDbEntry messageDbEntry = messageAndDbEntryMapper.mapToDbEntry(message);
        messageDbEntry.setTicket(ticketDbEntry);
        return messageDbEntry;
    }
}
