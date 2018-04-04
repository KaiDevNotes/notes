package root.application;

public interface UsersPresenter 
{
    void showUsersView(Message message);
    void showFormWithError(UserForm form, Message message);
}
