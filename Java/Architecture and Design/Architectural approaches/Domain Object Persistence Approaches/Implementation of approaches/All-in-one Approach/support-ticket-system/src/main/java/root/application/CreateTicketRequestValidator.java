package root.application;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static root.application.ValidationHelper.isValidDomainObjectId;

public class CreateTicketRequestValidator extends UseCaseRequestValidator<CreateTicketRequest>
{
    public CreateTicketRequestValidator(final UseCase<CreateTicketRequest> delegate) 
    {
        super(delegate);
    }
    
    @Override
    protected void validate(
        final CreateTicketRequest request, 
        final UseCaseRequestValidationResult validationResult)
    {
        if (!isValidDomainObjectId(request.getSubmitterId()))
        {
            validationResult.addValidationError("Invalid Submitter ID.");
        }
        if (isBlank(request.getIssueDescription()))
        {
            validationResult.addValidationError("Ticket description is empty.");
        }
    }
}
