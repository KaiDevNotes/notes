package root.application.validation;

import static root.application.validation.ValidationHelper.hasValidLength;
import static root.application.validation.ValidationHelper.isValidDomainObjectId;

import java.lang.reflect.Field;

import root.application.UseCaseRequest;

public class UseCaseRequestValidator
{
    public ValidationResult validate(final UseCaseRequest request)
    {
        final ValidationResult result = new ValidationResult();
        for (final Field field : getFields(request))
        {
            validateDomainObjectId(field, request, result);
            validateLength(field, request, result);
        }   
        return result;
    }
    
    private void validateDomainObjectId(
        final Field field, final UseCaseRequest request, final ValidationResult result)
    {
        if (!field.isAnnotationPresent(DomainObjectId.class))
        {
            return;
        }
        final String domainObjectId = (String) getValue(field, request);
        if (!isValidDomainObjectId(domainObjectId))
        {
            final String errorMessage = field.getAnnotation(DomainObjectId.class).errorMessage();
            result.addValidationError(errorMessage);
        }     
    }
    
    private void validateLength(
        final Field field, final UseCaseRequest request, final ValidationResult result)
    {
        if (!field.isAnnotationPresent(Length.class))
        {
            return;
        }
        final Length annotation = field.getAnnotation(Length.class);
        final int minLength = annotation.min();
        final int maxLength = annotation.max();
        final String value = (String) getValue(field, request);        
        if (!hasValidLength(value, minLength, maxLength))
        {
            result.addValidationError(annotation.errorMessage());
        }     
    }
    
    private Field[] getFields(final UseCaseRequest request)
    {
        return request.getClass().getDeclaredFields();        
    }
    
    private Object getValue(final Field field, final Object instanceReference)
    {
        try 
        {
            field.setAccessible(true);
            return field.get(instanceReference);            
        }
        catch (final IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
}
