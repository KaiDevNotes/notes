package root.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidationHelperTest
{
    @Test
    public void testIsValidDomainObjectId_true()
    {
        assertTrue(ValidationHelper.isValidDomainObjectId("0931431b-585e-474a-89ed-50b6fddaf815"));
    }
    
    @Test
    public void testIsValidDomainObjectId_shouldReturnFalseIfDomainObjectIdIsNull()
    {
        assertFalse(ValidationHelper.isValidDomainObjectId(null));
    }
    
    @Test
    public void testIsValidDomainObjectId_shouldReturnFalseForEmptyString()
    {
        assertFalse(ValidationHelper.isValidDomainObjectId(""));
    }
    
    @Test
    public void testIsValidDomainObjectId_shouldReturnFalseForIncorrectId()
    {
        assertFalse(ValidationHelper.isValidDomainObjectId("0931431b585e-474a-89ed-50b6fddaf815"));
    }
}
