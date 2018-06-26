package root.application;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static root.application.ValidationHelper.isValidDomainObjectId;

public class AddMessageRequestValidator extends UseCaseRequestValidator<AddMessageRequest>
{
    public AddMessageRequestValidator(UseCase delegate) 
    {
        super(delegate);
    }
    
    @Override
    protected void validate(
        AddMessageRequest request, 
        UseCaseRequestValidationResult validationResult)
    {
        if (!isValidDomainObjectId(request.getTicketId()))
        {
            validationResult.addValidationError("Invalid Ticket ID.");
        }
        if (!isValidDomainObjectId(request.getSenderId()))
        {
            validationResult.addValidationError("Invalid Sender ID.");
        }
        if (isBlank(request.getMessageText()))
        {
            validationResult.addValidationError("Message is empty.");
        }
    }
}
