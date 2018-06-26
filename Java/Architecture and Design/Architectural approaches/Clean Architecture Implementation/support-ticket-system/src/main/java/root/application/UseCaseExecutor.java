package root.application;

import java.util.Map;

public class UseCaseExecutor  
{
    private Map<Class<?>, UseCase> requestToUseCaseMap;

    public UseCaseExecutor(Map<Class<?>, UseCase> requestToUseCaseMap) 
    {
        this.requestToUseCaseMap = requestToUseCaseMap;
    }
    
    public void execute(UseCaseRequest request, UseCaseResponse response)
    {
        UseCase useCaseToExecute = requestToUseCaseMap.get(request.getClass());
        if (useCaseToExecute == null)
        {
            response.markAsFailed("Request is not supported.");
            return;
        }
        useCaseToExecute.execute(request, response);
    }
}
