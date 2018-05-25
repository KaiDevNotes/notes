package root.application;

import java.util.UUID;
import root.domain.User;

public class CreateTicketRequestTransformer 
{
    private final UserGateway userGateway;

    public CreateTicketRequestTransformer(UserGateway userGateway) 
    {
        this.userGateway = userGateway;
    }
    
    public void transform(CreateTicketRequest request)
    {
        if (request.hasErrors())
        {
            throw new UseCaseExecutionException();
        }
        User submitter = userGateway.findById(
            UUID.fromString(request.getSubmitterId()));
        if (submitter == null)
        {
            throw new UseCaseExecutionException();
        }
        request.setSubmitter(submitter);
    }
}
