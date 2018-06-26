package root.presentation;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import root.domain.DomainObject;

public class CreateTicketResponse extends AbstractUseCaseResponse
{
    public CreateTicketResponse(RedirectAttributes model) 
    {
        super(model);
    }
    
    @Override
    public void markAsSuccessful(DomainObject domainObject)
    {
        model.addFlashAttribute(SUCCESS_MESSAGE, 
            String.format("Ticket [%s] has been created successfully.", domainObject.getId()));
    }
}
