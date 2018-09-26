package root.infrastructure.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import root.domain.Message;

@Entity
@Table(name="message")
@Access(AccessType.FIELD)
public class MessageDbEntry extends Message implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    
    @Column(name="message_text")
    private String messageText;
    
    @Column(name="date") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Column(name="party")
    @Enumerated(EnumType.STRING)
    private ConversationParty party;
    
    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private TicketDbEntry ticket;
    
    public MessageDbEntry()
    {
    }
    
    public MessageDbEntry(
        final String messageText, final ConversationParty party, final TicketDbEntry ticket)
    {
        this.messageText = messageText;
        this.date = new Date();
        this.party = party;
        this.ticket = ticket;
    }
    
    @Override
    public UUID getId()
    {
        return id;
    }
    
    @Override
    public String getMessageText()
    {
        return messageText;
    }
    
    @Override
    public Date getDate()
    {
        return date;
    }
    
    @Override
    public ConversationParty getParty()
    {
        return party;
    }
}
