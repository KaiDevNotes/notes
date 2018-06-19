package root.application;

import java.util.UUID;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class ValidationHelper 
{
    private static final int LOGIN_MIN_LENGTH = 5;
    private static final int PASSWORD_MIN_LENGTH = 5;
    
    public static boolean isValidDomainObjectId(String domainObjectId)
    {
        try
        {
            UUID.fromString(domainObjectId);
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }
    
    public static boolean isValidLogin(String login)
    {
        return isNotBlank(login) && login.length() >= LOGIN_MIN_LENGTH;
    }
    
    public static boolean isValidPassword(String password)
    {
        return isNotBlank(password) && password.length() >= PASSWORD_MIN_LENGTH;
    }
}
