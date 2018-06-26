package root.application;

public class AddMessageRequest implements UseCaseRequest 
{
    private String ticketId;
    private String senderId;
    private String messageText;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) 
    {
        this.ticketId = ticketId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) 
    {
        this.senderId = senderId;
    }

    public String getMessageText() {
        return messageText;
    } 

    public void setMessageText(String messageText) 
    {
        this.messageText = messageText;
    }   
}
