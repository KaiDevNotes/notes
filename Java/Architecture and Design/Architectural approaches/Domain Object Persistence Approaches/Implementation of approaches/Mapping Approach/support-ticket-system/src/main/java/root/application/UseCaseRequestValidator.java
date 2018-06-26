package root.application;

public abstract class UseCaseRequestValidator<R extends UseCaseRequest> implements UseCase<R> 
{
    private final UseCase delegate;

    public UseCaseRequestValidator(UseCase delegate) 
    {
        this.delegate = delegate;
    }
    
    @Override
    public void execute(R request, UseCaseResponse response)
    {       
        UseCaseRequestValidationResult validationResult = new UseCaseRequestValidationResult();
        validate(request, validationResult);
        if (validationResult.isSuccessful())
        {
            delegate.execute(request, response);
        }   
        else 
        {
            String errorMessage = 
                validationResult.getValidationReport().stream().reduce(" ", String::concat);            
            response.markAsFailed(errorMessage);
        }
    }
    
    abstract protected void validate(R request, UseCaseRequestValidationResult validationResult);
}
