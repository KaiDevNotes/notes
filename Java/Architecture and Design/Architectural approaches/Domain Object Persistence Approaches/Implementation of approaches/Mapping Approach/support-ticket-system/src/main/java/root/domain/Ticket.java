package root.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Ticket extends DomainObject
{
    private final String issueDescription;
    private final List<Message> messages;
    private final Date creationDate;    
    private Date resolutionDate;
    private Status status;
    private final User submitter;
    
    private Ticket(final Builder builder)
    {
        super(builder.id);
        this.issueDescription = builder.issueDescription;
        this.messages = (builder.messages == null ? new ArrayList<>() : builder.messages);
        this.creationDate = (builder.creationDate == null ? new Date() : builder.creationDate);        
        this.resolutionDate = builder.resolutionDate;
        this.status = (builder.status == null ? Status.NEW : builder.status);
        this.submitter = builder.submitter;
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
        if (Status.RESOLVED.equals(status))
        {
            return;
        }
        
        final Message.ConversationParty party = getParty(sender);
        messages.add(createMessage(messageText, party));
        if (party.equals(Message.ConversationParty.SUPPORT))
        {
            status = Status.OPENED;
        }
    }
    
    private Message createMessage(
        final String messageText, final Message.ConversationParty party)
    {
        return new Message.Builder(messageText, party).build();
    }
    
    public void markAsResolved()
    {
        status = Status.RESOLVED;
        resolutionDate = new Date();
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
    
    public static class Builder
    {
        private String id;
        private final String issueDescription;
        private List<Message> messages;
        private Date creationDate;    
        private Date resolutionDate;
        private Status status;
        private final User submitter;
        
        public Builder(final String issueDescription, final User submitter)
        {
            this.issueDescription = issueDescription;
            this.submitter = submitter;
        }
        
        public Ticket build()
        {
            return new Ticket(this);
        }
        
        public Builder id(final String id)
        {
            this.id = id;
            return this;
        }
        
        public Builder messages(final List<Message> messages)
        {
            this.messages = messages;           
            return this;
        }
        
        public Builder creationDate(final Date creationDate)
        {
            this.creationDate = creationDate;
            return this;
        }
        
        public Builder resolutionDate(final Date resolutionDate)
        {
            this.resolutionDate = resolutionDate;
            return this;
        }
        
        public Builder status(final Status status)
        {
            this.status = status;
            return this;
        }      
    }
}
