package root.domain;

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

@Entity
@Table(name="message")
@Access(AccessType.FIELD)
public class Message implements DomainObject, Serializable 
{
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
    private Ticket ticket;
    
    public Message()
    {
    }

    public Message(String messageText, ConversationParty party, Ticket ticket) 
    {
        this.messageText = messageText;
        this.date = new Date();
        this.party = party;
        this.ticket = ticket;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getMessageText() {
        return messageText;
    }

    public Date getDate() {
        return date;
    }

    public ConversationParty getParty() {
        return party;
    }
    
    public enum ConversationParty 
    {
        SUBMITTER, SUPPORT;
    }
}
