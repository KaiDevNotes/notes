package root.application;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase
{
    private static final String SUCCESS_MESSAGE = 
        "User with id [%d] was successfully deleted.";
    
    private final UserGateway userGateway;
    
    public DeleteUserUseCaseImpl(UserGateway userGateway)
    {
        this.userGateway = userGateway;
    }
    
    @Override
    public void execute(Integer userId, UsersPresenter presenter)
    {
        userGateway.removeById(userId);
        
        String message = String.format(SUCCESS_MESSAGE, userId);        
        presenter.showUsersView(Message.newSuccessMessage(message));
    }
}
