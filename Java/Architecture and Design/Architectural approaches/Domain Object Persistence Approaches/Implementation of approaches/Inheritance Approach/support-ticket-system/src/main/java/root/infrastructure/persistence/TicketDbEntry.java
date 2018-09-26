package root.infrastructure.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import root.domain.Message;
import root.domain.Ticket;
import root.domain.User;

@Entity
@Table(name="ticket")
@Access(AccessType.FIELD)
public class TicketDbEntry extends Ticket implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    
    @Column(name="issue_description")
    private String issueDescription;
    
    @OneToMany(mappedBy="ticket", cascade = CascadeType.ALL)
    private List<MessageDbEntry> messages;
    
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
    private UserDbEntry submitter;
    
    public TicketDbEntry()
    {
    }
    
    public TicketDbEntry(final String issueDescription, final User submitter)
    {
        this.issueDescription = issueDescription;
        this.messages = new ArrayList<>();
        this.creationDate = new Date();
        this.status = Ticket.Status.NEW;
        this.submitter = (UserDbEntry) submitter;
    }
    
    @Override
    public UUID getId()
    {
        return id;
    }

    @Override
    public String getIssueDescription() 
    {
        return issueDescription;
    }

    @Override
    public List<Message> getMessages() 
    {
        return messages.stream()
            .map(messageDbEntry -> (Message) messageDbEntry)
            .collect(Collectors.toList());
    }

    @Override
    public Date getCreationDate() 
    {
        return creationDate;
    }

    @Override
    public Date getResolutionDate() 
    {
        return resolutionDate;
    }

    @Override
    public Ticket.Status getStatus() 
    {
        return status;
    }

    @Override
    protected void setResolutionDate(final Date resolutionDate) 
    {
        this.resolutionDate = resolutionDate;
    }

    @Override
    protected void setStatus(final Status status) 
    {
        this.status = status;
    }

    @Override
    protected User getSubmitter() 
    {
        return submitter;
    }   
    
    @Override
    protected void addMessage(final String messageText, final Message.ConversationParty party)
    {
        messages.add(new MessageDbEntry(messageText, party, this));
    }
}
