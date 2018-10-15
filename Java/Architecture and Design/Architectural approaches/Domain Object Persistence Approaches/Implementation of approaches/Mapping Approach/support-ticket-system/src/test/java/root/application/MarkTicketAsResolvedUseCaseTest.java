package root.application;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import root.domain.DomainObject;
import root.domain.Ticket;

@RunWith(MockitoJUnitRunner.class)
public class MarkTicketAsResolvedUseCaseTest
{
    @Mock
    private TicketGateway ticketGateway; 
    @Mock
    private UseCaseResponse response;
    @Mock
    private Ticket ticket;

    private MarkTicketAsResolvedRequest request;
    private MarkTicketAsResolvedUseCase useCase;
    
    @Before
    public void setUp()
    {
        request = new MarkTicketAsResolvedRequest();
        request.setTicketId("ticketId");
        
        useCase = new MarkTicketAsResolvedUseCase(ticketGateway);
    }
    
    @Test
    public void testExecute_success()
    {
        when(ticketGateway.findById("ticketId")).thenReturn(ticket);
        
        useCase.execute(request, response);

        verify(ticket, times(1)).markAsResolved();
        verify(ticketGateway, times(1)).save(ticket);
        verify(response, times(1)).markAsSuccessful(ticket);
        verify(response, times(0)).markAsFailed(any(String.class));
    }
    
    @Test
    public void testExecute_failure()
    {
        when(ticketGateway.findById("ticketId")).thenReturn(null);
        
        useCase.execute(request, response);

        verify(response, times(1)).markAsFailed(any(String.class));
        verify(ticket, times(0)).markAsResolved();
        verify(ticketGateway, times(0)).save(ticket);
        verify(response, times(0)).markAsSuccessful(any(DomainObject.class));
    }
}
