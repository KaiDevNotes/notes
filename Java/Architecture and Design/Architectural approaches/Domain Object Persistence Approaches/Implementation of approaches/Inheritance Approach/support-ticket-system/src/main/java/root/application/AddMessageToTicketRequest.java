package root.application;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static root.application.ValidationHelper.isValidDomainObjectId;
import root.domain.Ticket;
import root.domain.User;

public class AddMessageToTicketRequest 
{
    private String ticketId;
    private Ticket ticket;
    private String senderId;
    private User sender;
    private String messageText;
    
    public boolean hasErrors()
    {
        return !isValidDomainObjectId(ticketId) || 
                !isValidDomainObjectId(senderId) || 
                isBlank(messageText);
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) 
    {
        this.ticketId = ticketId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) 
    {
        this.ticket = ticket;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) 
    {
        this.senderId = senderId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) 
    {
        this.sender = sender;
    }

    public String getMessageText() {
        return messageText;
    } 

    public void setMessageText(String messageText) 
    {
        this.messageText = messageText;
    }   
}
