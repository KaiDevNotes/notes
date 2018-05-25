package root.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Ticket 
{
    private final String id;
    private final String issueDescription;
    private final List<Message> messages;
    private final Date creationDate;    
    private Date resolutionDate;
    private Status status;
    private final User submitter;
    
    private Ticket(Builder builder)
    {
        this.id = builder.id;
        this.issueDescription = builder.issueDescription;
        if (builder.messages == null)
        {
            this.messages = new ArrayList<>();
        }
        else 
        {
            this.messages = builder.messages;
        }
        if (builder.creationDate == null)
        {
            this.creationDate = new Date();
        }
        else 
        {
            this.creationDate = builder.creationDate;
        }
        
        this.resolutionDate = builder.resolutionDate;
        if (builder.status == null)
        {
            this.status = Status.NEW;
        }
        else
        {
            this.status = builder.status;
        }
        this.submitter = builder.submitter;
    }

    public String getId() 
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
    
    public void addMessage(String messageText, User sender)
    {
        if (ticketIsNotResolved())
        {
            Message.ConversationParty party = getParty(sender);
            messages.add(createMessage(messageText, party));
            if (party.equals(Message.ConversationParty.SUPPORT))
            {
                status = Status.OPENED;
            }
        }
    }
    
    private Message createMessage(
        String messageText, Message.ConversationParty party)
    {
        return new Message.Builder(messageText, party).build();
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
    
    private Message.ConversationParty getParty(User sender)
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
        private String issueDescription;
        private List<Message> messages;
        private Date creationDate;    
        private Date resolutionDate;
        private Status status;
        private User submitter;
        
        public Builder(String issueDescription, User submitter)
        {
            this.issueDescription = issueDescription;
            this.submitter = submitter;
        }
        
        public Ticket build()
        {
            return new Ticket(this);
        }
        
        public Builder id(String id)
        {
            this.id = id;
            return this;
        }
        
        public Builder messages(List<Message> messages)
        {
            this.messages = messages;           
            return this;
        }
        
        public Builder creationDate(Date creationDate)
        {
            this.creationDate = creationDate;
            return this;
        }
        
        public Builder resolutionDate(Date resolutionDate)
        {
            this.resolutionDate = resolutionDate;
            return this;
        }
        
        public Builder status(Status status)
        {
            this.status = status;
            return this;
        }      
    }
}
