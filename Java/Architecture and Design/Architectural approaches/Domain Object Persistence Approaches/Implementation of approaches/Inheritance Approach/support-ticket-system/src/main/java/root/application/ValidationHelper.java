package root.application;

import java.util.UUID;

public class ValidationHelper 
{    
    public static boolean isValidDomainObjectId(final String domainObjectId)
    {
        try
        {
            UUID.fromString(domainObjectId);
            return true;
        }
        catch (final IllegalArgumentException e)
        {
            return false;
        }
    }
}
