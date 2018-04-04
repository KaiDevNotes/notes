package root.application;

import java.util.Collection;
import root.domain.User;

public interface UserGateway 
{
    void save(User user);
    User findById(Integer id);
    Collection<User> findAll();
    void removeById(Integer id);
}
