package root.application;

public interface UseCase<R extends UseCaseRequest> 
{
    void execute(R request, UseCaseResponse response);
}
