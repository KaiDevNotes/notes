package root.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class UseCaseRequestValidationResultTest
{
    private Collection<String> errorMessages;
    private UseCaseRequestValidationResult validationResult;
    
    @Before
    public void setUp()
    {
        errorMessages = new ArrayList<>(Arrays.asList("message1", "message2"));
        validationResult = new UseCaseRequestValidationResult();
    }
    
    @Test
    public void testIsSuccessful_true()
    {
        assertTrue(validationResult.isSuccessful());
    }
    
    @Test
    public void testIsSuccessful_false()
    {
        errorMessages.forEach(message -> validationResult.addValidationError(message));
        
        assertFalse(validationResult.isSuccessful());
    }
    
    @Test
    public void testGetValidationReport_emptyValidationReportByDefault()
    {
        assertTrue(validationResult.getValidationReport().isEmpty());
    }
    
    @Test
    public void testGetValidationReport()
    {
        errorMessages.forEach(message -> validationResult.addValidationError(message));
        
        final Collection<String> actualErrorMessages = validationResult.getValidationReport();
        assertEquals(errorMessages.size(), actualErrorMessages.size());
        actualErrorMessages.forEach(message -> assertTrue(errorMessages.contains(message)));
    }
}
