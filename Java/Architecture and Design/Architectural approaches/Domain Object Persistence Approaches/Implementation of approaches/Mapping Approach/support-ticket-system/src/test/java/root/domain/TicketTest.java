package root.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TicketTest
{
    private User customer;
    private User supportEngineer;
    private Ticket ticket;
    
    @Before
    public void setUp()
    {
        customer = new User.Builder("login", "password", User.Role.CUSTOMER).id("1").build();
        supportEngineer = new User.Builder("login", "password", User.Role.SUPPORT_ENGINEER).id("2").build();
        ticket = new Ticket.Builder("issueDescription", customer).build();
    }
    
    @Test
    public void testNewTicket()
    {
        assertEquals("issueDescription", ticket.getIssueDescription());
        assertEquals(customer, ticket.getSubmitter());
        assertEquals(Ticket.Status.NEW, ticket.getStatus());
        assertNotNull(ticket.getCreationDate());
        assertNull(ticket.getResolutionDate());
        assertNull(ticket.getId());
        assertTrue(ticket.getMessages().isEmpty());
    }
    
    @Test
    public void testAddMessage_toNewTicketByCustomer()
    {
        ticket.addMessage("message", customer);
        
        assertEquals(Ticket.Status.NEW, ticket.getStatus());
        assertEquals(1, ticket.getMessages().size());
    }
    
    @Test
    public void testAddMessage_toOpenedTicketByCustomer()
    {
        final Ticket openedTicket = createOpenedTicket();
        
        openedTicket.addMessage("message", customer);
        
        assertEquals(Ticket.Status.OPENED, openedTicket.getStatus());
    }
    
    @Test
    public void testAddMessage_toNewTicketBySupportEngineer()
    {
        ticket.addMessage("message", supportEngineer);
        
        assertEquals(Ticket.Status.OPENED, ticket.getStatus());
        assertEquals(1, ticket.getMessages().size());
    }
    
    @Test
    public void testAddMessage_toOpenedTicketBySupportEngineer()
    {
        final Ticket openedTicket = createOpenedTicket();
        
        openedTicket.addMessage("message", supportEngineer);
        
        assertEquals(Ticket.Status.OPENED, openedTicket.getStatus());
    }
    
    @Test
    public void testAddMessage_shouldNotAddMessageToResolvedTicket()
    {
        ticket.addMessage("message1", supportEngineer);
        ticket.addMessage("message2", customer);
        ticket.markAsResolved();
        
        ticket.addMessage("message3", supportEngineer);
        ticket.addMessage("message4", customer);
        
        assertEquals(Ticket.Status.RESOLVED, ticket.getStatus());
        assertEquals(2, ticket.getMessages().size());
    }
    
    @Test
    public void testMarkAsResolved()
    {
        ticket.markAsResolved();
        
        assertEquals(Ticket.Status.RESOLVED, ticket.getStatus());
        assertNotNull(ticket.getResolutionDate());
    }
    
    private Ticket createOpenedTicket()
    {   
        ticket.addMessage("message1", supportEngineer);
        return ticket;
    }
}
