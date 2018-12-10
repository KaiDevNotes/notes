package root.application.usecases;

import root.application.UseCaseRequest;
import root.application.validation.DomainObjectId;
import root.application.validation.Length;

public class AddMessageRequest implements UseCaseRequest
{
    @DomainObjectId(errorMessage = "Ticket Id is invalid.")
    private String ticketId;
    
    @DomainObjectId(errorMessage = "Sender Id is invalid.")
    private String senderId;
    
    @Length(min = 1, max = 256,
        errorMessage = "Message should not be empty or longer than 256 characters.")
    private String messageText;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(final String ticketId) 
    {
        this.ticketId = ticketId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(final String senderId) 
    {
        this.senderId = senderId;
    }

    public String getMessageText() {
        return messageText;
    } 

    public void setMessageText(final String messageText) 
    {
        this.messageText = messageText;
    }   
}
