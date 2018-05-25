package root.domain;

import java.util.Date;

public class Message 
{
    private final String id;    
    private final String messageText;
    private final Date date;
    private final ConversationParty party;

    private Message(Builder builder) 
    {
        this.id = builder.id;
        this.messageText = builder.messageText;
        if (builder.date == null)
        {
            this.date = new Date();
        }
        else 
        {
            this.date = builder.date;
        }
        this.party = builder.party;
    }

    public String getId() {
        return id;
    }

    public String getMessageText() {
        return messageText;
    }

    public Date getDate() {
        return date;
    }

    public ConversationParty getParty() {
        return party;
    }
    
    public enum ConversationParty 
    {
        SUBMITTER, SUPPORT;
    }
    
    public static class Builder
    {
        private String id;    
        private String messageText;
        private Date date;
        private ConversationParty party;
        
        public Builder(String messageText, ConversationParty party)
        {
            this.messageText = messageText;
            this.party = party;
        }
        
        public Message build()
        {
            return new Message(this);
        }
        
        public Builder id(String id)
        {
            this.id = id;
            return this;
        }
        
        public Builder date(Date date)
        {
            this.date = date;
            return this;
        }
    }
}
