package root.application;

import java.util.Map;

public class UseCaseExecutor
{
    private final Map<Class<? extends UseCaseRequest>, UseCase<? extends UseCaseRequest>> requestToUseCaseMap;

    public UseCaseExecutor(final Map<Class<? extends UseCaseRequest>, UseCase<? extends UseCaseRequest>> requestToUseCaseMap) 
    {
        this.requestToUseCaseMap = requestToUseCaseMap;
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
	public void execute(final UseCaseRequest request, final UseCaseResponse response)
    {
        final UseCase useCaseToExecute = requestToUseCaseMap.get(request.getClass());
        if (useCaseToExecute == null)
        {
            response.markAsFailed("Request is not supported.");
            return;
        }
        useCaseToExecute.execute(request, response);
    }
}
