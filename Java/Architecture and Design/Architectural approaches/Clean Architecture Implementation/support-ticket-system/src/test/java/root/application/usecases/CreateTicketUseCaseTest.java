package root.application.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import root.application.TicketGateway;
import root.application.UseCaseResponse;
import root.application.UserGateway;
import root.domain.Ticket;
import root.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class CreateTicketUseCaseTest
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
    
    private CreateTicketRequest request;
    private CreateTicketUseCase useCase;
    
    @Before
    public void setUp()
    {
        request = new CreateTicketRequest();
        request.setSubmitterId("submitterId");
        request.setIssueDescription("issueDescription");
        
        useCase = new CreateTicketUseCase(userGateway, ticketGateway);
    }
    
    @Test
    public void testExecute_success()
    {
        final ArgumentCaptor<Ticket> ticketCaptor = ArgumentCaptor.forClass(Ticket.class);
        when(userGateway.findById("submitterId")).thenReturn(user);
        
        useCase.execute(request, response);
        
        verify(ticketGateway, times(1)).save(ticketCaptor.capture());
        
        final Ticket createdTicket = ticketCaptor.getValue();
        assertEquals("issueDescription", createdTicket.getIssueDescription());
        assertEquals(user, createdTicket.getSubmitter());
        assertEquals(Ticket.Status.NEW, createdTicket.getStatus());
        assertNotNull(createdTicket.getCreationDate());
        assertNull(createdTicket.getResolutionDate());
        assertNull(createdTicket.getId());
        assertTrue(createdTicket.getMessages().isEmpty());
        
        verify(response, times(1)).markAsSuccessful(ticketCaptor.capture());
        verify(response, times(0)).markAsFailed(any(String.class));
    }
    
    @Test
    public void testExecute_failure()
    {
        when(userGateway.findById("submitterId")).thenReturn(null);
        
        useCase.execute(request, response);
        
        verify(response, times(1)).markAsFailed(any(String.class));
        verify(ticketGateway, times(0)).save(any(Ticket.class));        
        verify(response, times(0)).markAsSuccessful(any(Ticket.class));
    }
}
