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
public class AddMessageRequestValidatorTest
{
    @Mock
    private UseCase<AddMessageRequest> delegat; 
    @Mock
    private UseCaseResponse response;
    
    private AddMessageRequestValidator validator;
    
    @Before
    public void setUp()
    {
        validator = new AddMessageRequestValidator(delegat);
    }
    
    @Test 
    public void testExecute_forCorrectRequest()
    {
        final AddMessageRequest request = new AddMessageRequest();
        request.setTicketId("0931431b-585e-474a-89ed-50b6fddaf815");
        request.setSenderId("31f3c1f4-e3f2-4d22-9b19-0ef44783a256");
        request.setMessageText("messageTest");
        
        validator.execute(request, response);
        
        verify(response, times(0)).markAsFailed(any(String.class));
        verify(delegat, times(1)).execute(request, response);
    }
    
    @Test 
    public void testExecute_forIncorrectRequest()
    {
        final AddMessageRequest request = new AddMessageRequest();
        
        validator.execute(request, response);
        
        verify(response, times(1)).markAsFailed(any(String.class));
        verify(delegat, times(0)).execute(request, response);
    }
}
