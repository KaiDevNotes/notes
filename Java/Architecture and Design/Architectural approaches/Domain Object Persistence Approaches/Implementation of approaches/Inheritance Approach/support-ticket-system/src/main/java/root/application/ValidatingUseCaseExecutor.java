package root.application;

import java.util.stream.Collectors;

import root.application.validation.UseCaseRequestValidator;
import root.application.validation.ValidationResult;

public class ValidatingUseCaseExecutor implements UseCaseExecutor
{
    private static final String SEPARATOR = " ";
    
    private final UseCaseExecutor delegat;
    private final UseCaseRequestValidator validator;
    
    public ValidatingUseCaseExecutor(final UseCaseExecutor delegat, final UseCaseRequestValidator validator)
    {
        this.delegat = delegat;
        this.validator = validator;
    } 
    
    @Override
    public void execute(final UseCaseRequest request, final UseCaseResponse response)
    {
        final ValidationResult result = validator.validate(request);
        if (result.isSuccessful())
        {
            delegat.execute(request, response);
        } 
        else 
        {
            final String errorMessage = result.getValidationReport()
                .stream().collect(Collectors.joining(SEPARATOR));             
            response.markAsFailed(errorMessage);
        }
    }   
}
