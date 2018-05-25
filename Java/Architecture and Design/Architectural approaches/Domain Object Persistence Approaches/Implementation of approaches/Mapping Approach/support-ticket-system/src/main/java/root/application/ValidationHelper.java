package root.application;

import java.util.UUID;

public class ValidationHelper 
{    
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
}
