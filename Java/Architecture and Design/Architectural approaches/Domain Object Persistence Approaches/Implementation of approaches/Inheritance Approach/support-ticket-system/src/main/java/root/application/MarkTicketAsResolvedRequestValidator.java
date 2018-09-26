package root.application;

import static root.application.ValidationHelper.isValidDomainObjectId;

public class MarkTicketAsResolvedRequestValidator extends UseCaseRequestValidator<MarkTicketAsResolvedRequest>
{
    public MarkTicketAsResolvedRequestValidator(final UseCase<MarkTicketAsResolvedRequest> delegate) 
    {
        super(delegate);
    }
    
    @Override
    protected void validate(
        final MarkTicketAsResolvedRequest request, 
        final UseCaseRequestValidationResult validationResult)
    {
        if (!isValidDomainObjectId(request.getTicketId()))
        {
            validationResult.addValidationError("Invalid Ticket ID.");
        }
    }
}
