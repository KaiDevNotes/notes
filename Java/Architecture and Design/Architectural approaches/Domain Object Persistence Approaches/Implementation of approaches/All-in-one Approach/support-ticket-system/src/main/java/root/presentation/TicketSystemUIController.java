package root.presentation;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import root.application.AddMessageToTicketRequest;
import root.application.AddMessageToTicketUseCase;
import root.application.CreateTicketRequest;
import root.application.CreateTicketUseCase;
import root.application.MarkTicketAsResolvedRequest;
import root.application.MarkTicketAsResolvedUseCase;
import root.application.TicketGateway;
import root.application.UseCaseExecutionException;
import root.application.UserGateway;

@Controller
public class TicketSystemUIController 
{
    private static final UUID CUSTOMER_ID = 
        UUID.fromString("1f60d19a-7c6c-4978-9f8c-2d6fb9c59cdc");
    private static final UUID SUPPORT_ENGINEER_ID = 
        UUID.fromString("8635956c-10cb-4c0b-820a-8b4d8423ccd7");
    
    private static final String CUSTOMER = "customer";
    private static final String SUPPORT_ENGINEER = "supportEngineer";
    private static final String TICKETS = "tickets";    
    private static final String SUCCESS_MESSAGE = "successMessage";
    private static final String FAILURE_MESSAGE = "failureMessage";
    
    private static final String TICKET_RESOLVED_MESSAGE_FORMAT = 
        "Issue described in ticket %s has been resolved";
    
    private static final String INVALID_REQUEST_MESSAGE = "Invalid request";
    
    private static final String APPLICATION_VIEW = "view";
    
    @Autowired
    private TicketGateway ticketGateway;
    
    @Autowired
    private UserGateway userGateway;
    
    @Autowired 
    private CreateTicketUseCase createTicketUseCase;
    
    @Autowired
    private AddMessageToTicketUseCase addMessageToTicketUseCase;
    
    @Autowired
    private MarkTicketAsResolvedUseCase markTicketAsResolvedUseCase;
    
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String openApplication(Model model)
    {
        model.addAttribute(TICKETS, ticketGateway.findAll());
        model.addAttribute(CUSTOMER, userGateway.findById(CUSTOMER_ID));
        model.addAttribute(SUPPORT_ENGINEER, userGateway.findById(SUPPORT_ENGINEER_ID));
        return APPLICATION_VIEW;
    }
    
    @RequestMapping(value="/ticket", method=RequestMethod.POST)
    public String createNewTicket(
        @Valid CreateTicketRequest request, RedirectAttributes model)
    {
        try
        {
            createTicketUseCase.execute(request);
        }
        catch (UseCaseExecutionException e)
        {
            model.addFlashAttribute(FAILURE_MESSAGE, INVALID_REQUEST_MESSAGE);
        }
        return "redirect:/";
    }
    
    @RequestMapping(value="/ticket/message", method=RequestMethod.POST)
    public String addMessageToTicket(
        @Valid AddMessageToTicketRequest request, RedirectAttributes model)
    {
        try
        {
            addMessageToTicketUseCase.execute(request);
        }
        catch (UseCaseExecutionException e)
        {
            model.addFlashAttribute(FAILURE_MESSAGE, INVALID_REQUEST_MESSAGE);
        }
        return "redirect:/";
    }
    
    @RequestMapping(value="/ticket/mark-as-resolved", method=RequestMethod.POST)
    public String markTicketAsResolved(
        @Valid MarkTicketAsResolvedRequest request, RedirectAttributes model)
    {
        try
        {
            markTicketAsResolvedUseCase.execute(request);
            model.addFlashAttribute(SUCCESS_MESSAGE, String.format(
                TICKET_RESOLVED_MESSAGE_FORMAT, request.getTicketId()));
        }
        catch (UseCaseExecutionException e)
        {
            model.addFlashAttribute(FAILURE_MESSAGE, INVALID_REQUEST_MESSAGE);
        }
        return "redirect:/";
    }
}
