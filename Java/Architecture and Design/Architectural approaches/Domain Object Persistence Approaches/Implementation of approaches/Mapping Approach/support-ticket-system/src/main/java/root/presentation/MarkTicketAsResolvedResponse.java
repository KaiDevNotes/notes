package root.presentation;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import root.domain.DomainObject;

public class MarkTicketAsResolvedResponse extends AbstractUseCaseResponse
{
    public MarkTicketAsResolvedResponse(RedirectAttributes model) 
    {
        super(model);
    }
    
    @Override
    public void markAsSuccessful(DomainObject domainObject)
    {
        model.addFlashAttribute(SUCCESS_MESSAGE, 
            String.format("Issue described in ticket [%s] has been resolved.", domainObject.getId()));
    }
}
