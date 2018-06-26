package root.application;

import static root.application.ValidationHelper.isValidDomainObjectId;

public class MarkTicketAsResolvedRequestValidator extends UseCaseRequestValidator<MarkTicketAsResolvedRequest>
{
    public MarkTicketAsResolvedRequestValidator(UseCase delegate) 
    {
        super(delegate);
    }
    
    @Override
    protected void validate(
        MarkTicketAsResolvedRequest request, 
        UseCaseRequestValidationResult validationResult)
    {
        if (!isValidDomainObjectId(request.getTicketId()))
        {
            validationResult.addValidationError("Invalid Ticket ID.");
        }
    }
}
