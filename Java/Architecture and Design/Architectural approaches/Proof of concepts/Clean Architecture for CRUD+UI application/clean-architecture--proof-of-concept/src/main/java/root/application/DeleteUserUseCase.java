package root.application;

public interface DeleteUserUseCase 
{
    void execute(Integer userId, UsersPresenter presenter);
}
