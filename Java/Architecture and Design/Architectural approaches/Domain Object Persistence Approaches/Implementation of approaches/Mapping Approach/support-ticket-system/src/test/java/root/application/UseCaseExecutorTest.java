package root.application;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseExecutorTest
{   
    @Mock
    private UseCaseResponse response;   
    @Mock
    private TestRequest1 request;
    @Mock
    private TestUseCase1 useCase;
    
    private UseCaseExecutor executor;
    
    @Before
    public void setUp()
    {
        final Map<Class<? extends UseCaseRequest>, UseCase<? extends UseCaseRequest>> requestToUseCaseMap = new HashMap<>();
        requestToUseCaseMap.put(request.getClass(), useCase);
        
        executor = new UseCaseExecutor(requestToUseCaseMap);
    }
    
    @Test
    public void testExecute_success()
    {
        executor.execute(request, response);
        
        verify(useCase, times(1)).execute(request, response);
        verify(response, times(0)).markAsFailed(any(String.class));
    }
    
    @Test
    public void testExecute_forUnsupportedRequest()
    { 
        final TestRequest2 unsupportedRequest = new TestRequest2();
        
        executor.execute(unsupportedRequest, response);
        
        verify(response, times(1)).markAsFailed(any(String.class));
        verify(useCase, times(0)).execute(request, response);
    }
    
    private class TestRequest1 implements UseCaseRequest
    {        
    }
    
    private class TestRequest2 implements UseCaseRequest
    {        
    }
    
    private class TestUseCase1 implements UseCase<TestRequest1>
    {
        @Override
        public void execute(final TestRequest1 request, final UseCaseResponse response)
        {            
        }
    }
}
