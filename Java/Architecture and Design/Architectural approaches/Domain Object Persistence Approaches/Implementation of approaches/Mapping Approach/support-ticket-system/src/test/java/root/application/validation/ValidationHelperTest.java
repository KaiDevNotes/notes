package root.application.validation;

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
    
    @Test 
    public void testHasValidLength_true()
    {
        assertTrue(ValidationHelper.hasValidLength("hello world", 1, 12));
    }
    
    @Test 
    public void testHasValidLength_shouldReturnFalseForNull()
    {
        assertFalse(ValidationHelper.hasValidLength(null, 1, 5));
    }
    
    @Test 
    public void testHasValidLength_shouldReturnFalseForEmptyString()
    {
        assertFalse(ValidationHelper.hasValidLength("", 4, 7));
    }
    
    @Test 
    public void testHasValidLength_shouldReturnFalseForShorterString()
    {
        assertFalse(ValidationHelper.hasValidLength("hi", 3, 20));
    }
    
    @Test 
    public void testHasValidLength_shouldReturnFalseForLongerString()
    {
        assertFalse(ValidationHelper.hasValidLength("verrry looong string", 1, 10));
    }
}
