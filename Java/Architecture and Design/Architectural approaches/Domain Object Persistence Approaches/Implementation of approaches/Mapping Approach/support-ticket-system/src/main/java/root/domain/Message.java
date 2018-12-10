package root.domain;

import java.util.Date;

public class Message extends DomainObject
{
    private final String messageText;
    private final Date date;
    private final ConversationParty party;

    private Message(final Builder builder) 
    {
        super(builder.id);
        this.messageText = builder.messageText;
        this.date = (builder.date == null ? new Date() : builder.date);
        this.party = builder.party;
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
        private final String messageText;
        private Date date;
        private final ConversationParty party;
        
        public Builder(final String messageText, final ConversationParty party)
        {
            this.messageText = messageText;
            this.party = party;
        }
        
        public Message build()
        {
            return new Message(this);
        }
        
        public Builder id(final String id)
        {
            this.id = id;
            return this;
        }
        
        public Builder date(final Date date)
        {
            this.date = date;
            return this;
        }
    }
}
