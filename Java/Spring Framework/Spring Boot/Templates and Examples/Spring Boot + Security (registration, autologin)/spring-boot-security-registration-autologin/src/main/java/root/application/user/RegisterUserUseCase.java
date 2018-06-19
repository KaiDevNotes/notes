package root.application.user;

import root.application.UseCaseExecutionException;
import root.application.UserGateway;
import root.domain.User;

public class RegisterUserUseCase 
{
    private final UserGateway userGateway;
    
    public RegisterUserUseCase(UserGateway userGateway)
    {
        this.userGateway = userGateway;
    }
    
    public void execute(RegisterUserRequest request)
    {
        if (request.hasErrors())
        {
            throw new UseCaseExecutionException(
                "Login and Password should contain at least 5 characters");
        }
        User newUser = new User.Builder(
            request.getLogin(), request.getPassword(), User.Role.USER).build();
        userGateway.save(newUser);
    }
}
