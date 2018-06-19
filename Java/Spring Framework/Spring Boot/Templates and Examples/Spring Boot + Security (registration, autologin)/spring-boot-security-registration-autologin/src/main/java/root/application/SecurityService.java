package root.application;

import root.domain.User;

public interface SecurityService 
{       
    void login(User user);
    void login(String login, String password);
    User getLoggedInUser();
}
