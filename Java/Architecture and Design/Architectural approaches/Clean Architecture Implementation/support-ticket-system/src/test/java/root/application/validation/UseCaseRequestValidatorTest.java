package root.application.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import root.application.UseCaseRequest;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseRequestValidatorTest
{
    private static final String VALIDATION_ERROR_MESSAGE = "Validation error";
    
    private UseCaseRequestValidator validator;
    
    @Before
    public void setUp()
    {
        validator = new UseCaseRequestValidator();
    }
    
    @Test
    public void testValidate_validateDomainObjectId_success()
    {
        final UseCaseRequest request = 
            new UseCaseRequestWithDomainObjectId("0931431b-585e-474a-89ed-50b6fddaf815");
        
        final ValidationResult result = validator.validate(request);
        
        assertTrue(result.isSuccessful());
        assertTrue(result.getValidationReport().isEmpty());        
    }
    
    @Test
    public void testValidate_validateDomainObjectId_fail()
    {
        final UseCaseRequest request = 
            new UseCaseRequestWithDomainObjectId("0931431b-585e-474a89ed-50b6fddaf815");
        
        final ValidationResult result = validator.validate(request);
        
        assertFalse(result.isSuccessful());
        assertEquals(1, result.getValidationReport().size());
        assertEquals(VALIDATION_ERROR_MESSAGE, result.getValidationReport().iterator().next());
    }
    
    private class UseCaseRequestWithDomainObjectId implements UseCaseRequest
    {        
        @DomainObjectId(errorMessage = VALIDATION_ERROR_MESSAGE)
        private final String domainObjectId;
        
        public UseCaseRequestWithDomainObjectId(final String domainObjectId)
        {
            this.domainObjectId = domainObjectId;
        }
    }
    
    @Test
    public void testValidate_validateLength_success()
    {
        final UseCaseRequest request = 
            new UseCaseRequestWithLength("String with allowed length");
        
        final ValidationResult result = validator.validate(request);
        
        assertTrue(result.isSuccessful());
        assertTrue(result.getValidationReport().isEmpty());
    }  
    
    @Test
    public void testValidate_validateLength_fail()
    {
        final UseCaseRequest request = 
            new UseCaseRequestWithLength("");
        
        final ValidationResult result = validator.validate(request);
        
        assertFalse(result.isSuccessful());
        assertEquals(1, result.getValidationReport().size());
        assertEquals(VALIDATION_ERROR_MESSAGE, result.getValidationReport().iterator().next());
    }
    
    private class UseCaseRequestWithLength implements UseCaseRequest
    {        
        @Length(min = 5, max = 32,
            errorMessage = VALIDATION_ERROR_MESSAGE)
        private final String aString;
        
        public UseCaseRequestWithLength(final String aString)
        {
            this.aString = aString;
        }
    }
}
