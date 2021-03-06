package root.presentation;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import root.domain.DomainObject;

public class AddMessageResponse extends AbstractUseCaseResponse
{
    public AddMessageResponse(final RedirectAttributes model) 
    {
        super(model);
    }
    
    @Override
    public void markAsSuccessful(final DomainObject domainObject)
    {
        model.addFlashAttribute(SUCCESS_MESSAGE, 
            String.format("Your message has been successfully added to ticket [%s].", domainObject.getId()));
    }
}
