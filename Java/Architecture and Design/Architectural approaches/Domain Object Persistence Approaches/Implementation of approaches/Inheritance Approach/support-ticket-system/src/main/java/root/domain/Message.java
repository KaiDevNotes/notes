package root.domain;

import java.util.Date;

public abstract class Message 
{
    public abstract String getMessageText();
    public abstract Date getDate();
    public abstract ConversationParty getParty(); 
    
    public enum ConversationParty 
    {
        SUBMITTER, SUPPORT;
    }
}
