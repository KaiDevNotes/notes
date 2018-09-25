package root.application;

import root.domain.DomainObject;

public interface UseCaseResponse
{   
    void markAsSuccessful(DomainObject domainObject);
    void markAsFailed(String errorMessage);
}
