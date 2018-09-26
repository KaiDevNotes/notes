package root.infrastructure.persistence;

import java.io.Serializable;
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

import root.domain.Ticket;

@Entity
@Table(name="ticket")
@Access(AccessType.FIELD)
public class TicketDbEntry implements DbEntry, Serializable
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

    public UUID getId() 
    {
        return id;
    }

    public void setId(final UUID id) 
    {
        this.id = id;
    }

    public String getIssueDescription() 
    {
        return issueDescription;
    }

    public void setIssueDescription(final String issueDescription) 
    {
        this.issueDescription = issueDescription;
    }

    public List<MessageDbEntry> getMessages() 
    {
        return messages;
    }

    public void setMessages(final List<MessageDbEntry> messages) 
    {
        this.messages = messages;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public Date getResolutionDate()
    {
        return resolutionDate;
    }

    public void setResolutionDate(final Date resolutionDate) 
    {
        this.resolutionDate = resolutionDate;
    }

    public Ticket.Status getStatus() 
    {
        return status;
    }

    public void setStatus(final Ticket.Status status)
    {
        this.status = status;
    }

    public UserDbEntry getSubmitter()
    {
        return submitter;
    }

    public void setSubmitter(final UserDbEntry submitter)
    {
        this.submitter = submitter;
    }
}
