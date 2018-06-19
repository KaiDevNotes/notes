package root.application;

public class UseCaseExecutionException extends RuntimeException
{    
    public UseCaseExecutionException()
    {
    }
    
    public UseCaseExecutionException(String message)
    {
        super(message);
    }
}
