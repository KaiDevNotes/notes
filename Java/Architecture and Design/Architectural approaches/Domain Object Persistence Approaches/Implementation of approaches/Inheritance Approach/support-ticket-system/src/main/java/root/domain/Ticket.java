package root.domain;

import java.util.Date;
import java.util.List;

public abstract class Ticket 
{
    public abstract String getDomainId();
    public abstract String getIssueDescription();
    public abstract List<Message> getMessages();
    public abstract Date getCreationDate();
    public abstract Date getResolutionDate();
    public abstract Status getStatus();
    
    protected abstract void setResolutionDate(Date resolutionDate);
    protected abstract void setStatus(Status status);
    protected abstract User getSubmitter();
    
    public void addMessage(String messageText, User sender)
    {
        if (ticketIsNotResolved())
        {
            Message.ConversationParty party = getParty(sender);
            addMessage(messageText, party);
            if (party.equals(Message.ConversationParty.SUPPORT))
            {
                setStatus(Status.OPENED);
            }
        }
    }
    
    protected abstract void addMessage(
        String messageText, Message.ConversationParty party);
    
    public void markAsResolved()
    {
        setStatus(Status.RESOLVED);
        setResolutionDate(new Date());
    }
    
    private boolean ticketIsNotResolved()
    {
        return !getStatus().equals(Status.RESOLVED);
    }
    
    private Message.ConversationParty getParty(User sender)
    {
        if (getSubmitter().equals(sender))
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
