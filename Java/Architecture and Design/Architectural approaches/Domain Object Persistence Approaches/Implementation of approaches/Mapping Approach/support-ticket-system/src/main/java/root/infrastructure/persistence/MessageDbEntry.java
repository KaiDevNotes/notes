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

import root.domain.Message.ConversationParty;

@Entity
@Table(name="message")
@Access(AccessType.FIELD)
public class MessageDbEntry implements Serializable
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

    public UUID getId() 
    {
        return id;
    }

    public void setId(final UUID id) 
    {
        this.id = id;
    }

    public String getMessageText()
    {
        return messageText;
    }

    public void setMessageText(final String messageText)
    {
        this.messageText = messageText;
    }

    public Date getDate() 
    {
        return date;
    }

    public void setDate(final Date date) 
    {
        this.date = date;
    }

    public ConversationParty getParty() 
    {
        return party;
    }

    public void setParty(final ConversationParty party) 
    {
        this.party = party;
    }

    public TicketDbEntry getTicket()
    {
        return ticket;
    }

    public void setTicket(final TicketDbEntry ticket)
    {
        this.ticket = ticket;
    }
}
