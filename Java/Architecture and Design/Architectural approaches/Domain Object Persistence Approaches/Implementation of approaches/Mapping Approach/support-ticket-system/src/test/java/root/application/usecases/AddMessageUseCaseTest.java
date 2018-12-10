package root.application.usecases;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import root.application.TicketGateway;
import root.application.UseCaseResponse;
import root.application.UserGateway;
import root.domain.DomainObject;
import root.domain.Ticket;
import root.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class AddMessageUseCaseTest
{
    @Mock
    private UserGateway userGateway;
    @Mock
    private TicketGateway ticketGateway; 
    @Mock
    private UseCaseResponse response;
    @Mock
    private Ticket ticket;
    @Mock
    private User user;

    private AddMessageRequest request;
    private AddMessageUseCase useCase;
    
    @Before
    public void setUp()
    {
        request = new AddMessageRequest();
        request.setTicketId("ticketId");
        request.setSenderId("userId");
        request.setMessageText("messageTest");
        
        useCase = new AddMessageUseCase(userGateway, ticketGateway);
    }
    
    @Test
    public void testExecute_success()
    {
        when(userGateway.findById("userId")).thenReturn(user);
        when(ticketGateway.findById("ticketId")).thenReturn(ticket);
        
        useCase.execute(request, response);

        verify(ticket, times(1)).addMessage("messageTest", user);
        verify(ticketGateway, times(1)).save(ticket);
        verify(response, times(1)).markAsSuccessful(ticket);
        verify(response, times(0)).markAsFailed(any(String.class));
    }
    
    @Test
    public void testExecute_failure()
    {
        when(userGateway.findById("userId")).thenReturn(null);
        when(ticketGateway.findById("ticketId")).thenReturn(null);
        
        useCase.execute(request, response);

        verify(response, times(1)).markAsFailed(any(String.class));
        verify(ticket, times(0)).addMessage("messageTest", user);
        verify(ticketGateway, times(0)).save(ticket);
        verify(response, times(0)).markAsSuccessful(any(DomainObject.class));
    }
}
