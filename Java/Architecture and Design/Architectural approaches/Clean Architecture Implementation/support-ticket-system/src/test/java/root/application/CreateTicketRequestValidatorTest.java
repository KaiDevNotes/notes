package root.application;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateTicketRequestValidatorTest
{
    @Mock
    private UseCase<CreateTicketRequest> delegat; 
    @Mock
    private UseCaseResponse response;
    
    private CreateTicketRequestValidator validator;
    
    @Before
    public void setUp()
    {
        validator = new CreateTicketRequestValidator(delegat);
    }
    
    @Test 
    public void testExecute_forCorrectRequest()
    {
        final CreateTicketRequest request = new CreateTicketRequest();
        request.setSubmitterId("0931431b-585e-474a-89ed-50b6fddaf815");        
        request.setIssueDescription("issueDescription");
        
        validator.execute(request, response);
        
        verify(response, times(0)).markAsFailed(any(String.class));
        verify(delegat, times(1)).execute(request, response);
    }
    
    @Test 
    public void testExecute_forIncorrectRequest()
    {
        final CreateTicketRequest request = new CreateTicketRequest();
        
        validator.execute(request, response);
        
        verify(response, times(1)).markAsFailed(any(String.class));
        verify(delegat, times(0)).execute(request, response);
    }
}
