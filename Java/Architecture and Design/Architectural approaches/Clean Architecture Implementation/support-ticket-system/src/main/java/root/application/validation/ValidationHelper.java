package root.application.validation;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.UUID;

public class ValidationHelper
{        
    public static boolean isValidDomainObjectId(final String domainObjectId)
    {
        if (domainObjectId == null)
        {
            return false;
        }
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
    
    public static boolean hasValidLength(final String inputString, final int min, final int max)
    {
        return isNotBlank(inputString) && inputString.length() >= min && inputString.length() <= max;
    }
}
