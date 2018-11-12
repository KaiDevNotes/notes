package root.application;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import root.application.validation.UseCaseRequestValidator;
import root.application.validation.ValidationResult;

@RunWith(MockitoJUnitRunner.class)
public class ValidatingUseCaseExecutorTest
{
    private static final String VALIDATION_ERROR_MESSAGE = "Validation error";
    
    @Mock
    private UseCaseExecutor delegat;
    @Mock
    private UseCaseRequestValidator validator;
    @Mock
    private ValidationResult validationResult;
    @Mock
    private UseCaseRequest request;
    @Mock
    private UseCaseResponse response;
    
    private ValidatingUseCaseExecutor validatingExecutor;
    
    @Before
    public void setUp()
    {
        validatingExecutor = new ValidatingUseCaseExecutor(delegat, validator);
    }
    
    @Test
    public void testExecute_success()
    {
        when(validationResult.isSuccessful()).thenReturn(true);
        when(validator.validate(request)).thenReturn(validationResult);
        
        validatingExecutor.execute(request, response);
        
        verify(delegat, times(1)).execute(request, response);
        verify(response, never()).markAsFailed(any(String.class));
    }
    
    @Test
    public void testExecute_fail()
    {
        when(validationResult.isSuccessful()).thenReturn(false);
        when(validationResult.getValidationReport()).thenReturn(Arrays.asList(VALIDATION_ERROR_MESSAGE));
        when(validator.validate(request)).thenReturn(validationResult);
        
        validatingExecutor.execute(request, response);
        
        verify(delegat, never()).execute(request, response);
        verify(response, times(1)).markAsFailed(VALIDATION_ERROR_MESSAGE);
    }
}
