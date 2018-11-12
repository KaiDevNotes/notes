package root.application;

import java.util.Map;

public class UseCaseExecutorImpl implements UseCaseExecutor
{
    private static final String ERROR_MESSAGE_FORMAT = "Request [%s] is not supported.";
    
    private final Map<Class<? extends UseCaseRequest>, UseCase<? extends UseCaseRequest>> requestToUseCaseMap;

    public UseCaseExecutorImpl(final Map<Class<? extends UseCaseRequest>, UseCase<? extends UseCaseRequest>> requestToUseCaseMap) 
    {
        this.requestToUseCaseMap = requestToUseCaseMap;
    }
    
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void execute(final UseCaseRequest request, final UseCaseResponse response)
    {
        final UseCase useCaseToExecute = requestToUseCaseMap.get(request.getClass());
        if (useCaseToExecute == null)
        {
            final String message = String.format(ERROR_MESSAGE_FORMAT, request.getClass().getSimpleName());
            response.markAsFailed(message);
            return;
        }
        useCaseToExecute.execute(request, response);
    }
}
