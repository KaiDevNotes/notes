package root.application;

public class Message 
{
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    
    private final String type;
    private final String text;
    
    private Message(String type, String text)
    {
        this.type = type;
        this.text = text;
    }

    public String getType() 
    {
        return type;
    }

    public String getText() 
    {
        return text;
    }
    
    public static Message newSuccessMessage(String text)
    {
        return new Message(SUCCESS, text);
    }
    
    public static Message newErrorMessage(String text)
    {
        return new Message(ERROR, text);
    }
}
