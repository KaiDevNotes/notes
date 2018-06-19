package root.application;

import root.domain.User;

public interface UserGateway extends DomainObjectGateway<User>
{   
    User findByLogin(String login);
}
