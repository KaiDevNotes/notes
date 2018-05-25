package root.infrastructure.persistence;

import java.util.UUID;
import org.springframework.stereotype.Component;
import root.domain.Message;

@Component
public class MessageAndDbEntryMapper
    implements DomainObjectAndDbEntityMapper<Message, MessageDbEntry> 
{
    @Override
    public MessageDbEntry mapToDbEntity(Message message)
    {
        MessageDbEntry messageDbEntry = new MessageDbEntry();
        if (message.getId() != null)
        {
            messageDbEntry.setId(UUID.fromString(message.getId()));
        } 
        messageDbEntry.setMessageText(message.getMessageText());
        messageDbEntry.setDate(message.getDate());
        messageDbEntry.setParty(message.getParty());
        return messageDbEntry;
    }
    
    @Override
    public Message mapToDomainObject(MessageDbEntry messageDbEntry)
    {
        return new Message.Builder(
                messageDbEntry.getMessageText(), messageDbEntry.getParty())
            .id(messageDbEntry.getId().toString())
            .date(messageDbEntry.getDate())
            .build();
    }
}
