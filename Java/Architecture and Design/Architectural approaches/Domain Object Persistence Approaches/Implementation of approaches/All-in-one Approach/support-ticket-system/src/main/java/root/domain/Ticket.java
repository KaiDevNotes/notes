package root.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ticket")
@Access(AccessType.FIELD)
public class Ticket implements DomainObject, Serializable  
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    
    @Column(name="issue_description")
    private String issueDescription;
    
    @OneToMany(mappedBy="ticket", cascade = CascadeType.ALL)
    private List<Message> messages;
    
    @Column(name="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;    
    
    @Column(name="resolution_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolutionDate;
    
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Ticket.Status status;
    
    @ManyToOne
    @JoinColumn(name = "submitter_id", referencedColumnName = "id")
    private User submitter;
    
    public Ticket()
    {
    }
    
    public Ticket(final String issueDescription, final User submitter)
    {
        this.issueDescription = issueDescription;
        this.messages = new ArrayList<>();
        this.creationDate = new Date();
        this.status = Status.NEW;
        this.submitter = submitter;
    }

    @Override
    public UUID getId() 
    {
        return id;
    }

    public String getIssueDescription() 
    {
        return issueDescription;
    }

    public List<Message> getMessages() 
    {
        return Collections.unmodifiableList(messages);
    }

    public Date getCreationDate() 
    {
        return creationDate;
    }

    public Date getResolutionDate() 
    {
        return resolutionDate;
    }

    public Status getStatus() 
    {
        return status;
    }

    public User getSubmitter() 
    {
        return submitter;
    }
    
    public void addMessage(final String messageText, final User sender)
    {
        if (ticketIsNotResolved())
        {
            final Message.ConversationParty party = getParty(sender);
            messages.add(new Message(messageText, party, this));
            if (party.equals(Message.ConversationParty.SUPPORT))
            {
                status = Status.OPENED;
            }
        }
    }
    
    public void markAsResolved()
    {
        status = Status.RESOLVED;
        resolutionDate = new Date();
    }
    
    private boolean ticketIsNotResolved()
    {
        return !status.equals(Status.RESOLVED);
    }
    
    private Message.ConversationParty getParty(final User sender)
    {
        if (submitter.equals(sender))
        {
            return Message.ConversationParty.SUBMITTER;
        }
        return Message.ConversationParty.SUPPORT;
    }
    
    public enum Status
    {
        NEW, OPENED, RESOLVED;
    }
}
