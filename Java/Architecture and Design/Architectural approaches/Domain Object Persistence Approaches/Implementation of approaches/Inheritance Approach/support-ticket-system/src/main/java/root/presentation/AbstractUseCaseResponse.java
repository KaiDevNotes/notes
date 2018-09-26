package root.presentation;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import root.application.UseCaseResponse;
import root.domain.DomainObject;

public abstract class AbstractUseCaseResponse implements UseCaseResponse
{    
    protected static final String SUCCESS_MESSAGE = "successMessage";
    protected static final String FAILURE_MESSAGE = "failureMessage";
    
    protected RedirectAttributes model;

    protected AbstractUseCaseResponse(final RedirectAttributes model) 
    {
        this.model = model;
    }
    
    @Override
    public abstract void markAsSuccessful(DomainObject domainObject);
    
    @Override
    public void markAsFailed(final String errorMessage)
    {
        model.addFlashAttribute(FAILURE_MESSAGE, errorMessage);
    }
}
