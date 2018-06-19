package root.application.user;

import static root.application.ValidationHelper.isValidLogin;
import static root.application.ValidationHelper.isValidPassword;

public class RegisterUserRequest 
{
    private String login;
    private String password;
    
    public boolean hasErrors()
    {
        return !isValidLogin(login) || !isValidPassword(password);
    }

    public String getLogin() 
    {
        return login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
}
