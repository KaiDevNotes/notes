package root.application;

import root.domain.User;

public class SaveUserUseCaseImpl implements SaveUserUseCase
{
    private static final String ERROR_MESSAGE = 
        "Form contains incorrect values or not all fields were filled.";
    
    private static final String SUCCESS_MESSAGE = 
        "Form data was successfully saved.";
    
    private final UserGateway userGateway;
    
    public SaveUserUseCaseImpl(UserGateway userGateway)
    {
        this.userGateway = userGateway;
    }
    
    @Override
    public void execute(UserForm form, UsersPresenter presenter)
    {
        if (form.hasErrors())
        {
            presenter.showFormWithError(form, Message.newErrorMessage(ERROR_MESSAGE));
        }
        else 
        {
            saveUser(form);
            presenter.showUsersView(Message.newSuccessMessage(SUCCESS_MESSAGE));
        }
    }
    
    private void saveUser(UserForm form)
    {
        User user = form.toDomainObject();
        userGateway.save(user);
    }
}
