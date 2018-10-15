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
public class MarkTicketAsResolvedRequestValidatorTest
{
    @Mock
    private UseCase<MarkTicketAsResolvedRequest> delegat; 
    @Mock
    private UseCaseResponse response;
    
    private MarkTicketAsResolvedRequestValidator validator;
    
    @Before
    public void setUp()
    {
        validator = new MarkTicketAsResolvedRequestValidator(delegat);
    }
    
    @Test 
    public void testExecute_forCorrectRequest()
    {
        final MarkTicketAsResolvedRequest request = new MarkTicketAsResolvedRequest();
        request.setTicketId("0931431b-585e-474a-89ed-50b6fddaf815");  
        
        validator.execute(request, response);
        
        verify(response, times(0)).markAsFailed(any(String.class));
        verify(delegat, times(1)).execute(request, response);
    }
    
    @Test 
    public void testExecute_forIncorrectRequest()
    {
        final MarkTicketAsResolvedRequest request = new MarkTicketAsResolvedRequest();
        
        validator.execute(request, response);
        
        verify(response, times(1)).markAsFailed(any(String.class));
        verify(delegat, times(0)).execute(request, response);
    }
}
