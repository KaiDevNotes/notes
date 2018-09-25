package root.application;

public abstract class UseCaseRequestValidator<R extends UseCaseRequest> implements UseCase<R> 
{
    private final UseCase<R> delegate;

    public UseCaseRequestValidator(final UseCase<R> delegate) 
    {
        this.delegate = delegate;
    }
    
    @Override
    public void execute(final R request, final UseCaseResponse response)
    {       
        final UseCaseRequestValidationResult validationResult = new UseCaseRequestValidationResult();
        validate(request, validationResult);
        if (validationResult.isSuccessful())
        {
            delegate.execute(request, response);
        }   
        else 
        {
            final String errorMessage = 
                validationResult.getValidationReport().stream().reduce(" ", String::concat);            
            response.markAsFailed(errorMessage);
        }
    }
    
    abstract protected void validate(R request, UseCaseRequestValidationResult validationResult);
}
