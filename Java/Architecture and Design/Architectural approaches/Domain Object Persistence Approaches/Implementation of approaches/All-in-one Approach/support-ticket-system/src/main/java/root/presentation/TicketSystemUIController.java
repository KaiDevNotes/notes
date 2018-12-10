package root.presentation;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import root.application.TicketGateway;
import root.application.UseCaseExecutor;
import root.application.UserGateway;
import root.application.usecases.AddMessageRequest;
import root.application.usecases.CreateTicketRequest;
import root.application.usecases.MarkTicketAsResolvedRequest;

@Controller
public class TicketSystemUIController 
{
    private static final UUID CUSTOMER_ID = UUID.fromString("1f60d19a-7c6c-4978-9f8c-2d6fb9c59cdc");
    private static final UUID SUPPORT_ENGINEER_ID = UUID.fromString("8635956c-10cb-4c0b-820a-8b4d8423ccd7");
    
    private static final String CUSTOMER = "customer";
    private static final String SUPPORT_ENGINEER = "supportEngineer";
    private static final String TICKETS = "tickets";    
    private static final String APPLICATION_VIEW = "view";
    
    @Autowired
    private UserGateway userGateway;
    
    @Autowired
    private TicketGateway ticketGateway;
    
    @Autowired 
    private UseCaseExecutor useCaseExecutor;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String openApplication(final Model model)
    {
        model.addAttribute(TICKETS, ticketGateway.findAll());
        model.addAttribute(CUSTOMER, userGateway.findById(CUSTOMER_ID));
        model.addAttribute(SUPPORT_ENGINEER, userGateway.findById(SUPPORT_ENGINEER_ID));
        return APPLICATION_VIEW;
    }
    
    @RequestMapping(value="/ticket", method=RequestMethod.POST)
    public String createNewTicket(
        @Valid final CreateTicketRequest request, final RedirectAttributes model)
    {
        final CreateTicketResponse response = new CreateTicketResponse(model);
        useCaseExecutor.execute(request, response);
        return "redirect:/";
    }
    
    @RequestMapping(value="/ticket/message", method=RequestMethod.POST)
    public String addMessageToTicket(
        @Valid final AddMessageRequest request, final RedirectAttributes model)
    {
        final AddMessageResponse response = new AddMessageResponse(model);
        useCaseExecutor.execute(request, response);
        return "redirect:/";
    }
    
    @RequestMapping(value="/ticket/mark-as-resolved", method=RequestMethod.POST)
    public String markTicketAsResolved(
        @Valid final MarkTicketAsResolvedRequest request, final RedirectAttributes model)
    {
        final MarkTicketAsResolvedResponse response = new MarkTicketAsResolvedResponse(model);
        useCaseExecutor.execute(request, response);
        return "redirect:/";
    }
}
