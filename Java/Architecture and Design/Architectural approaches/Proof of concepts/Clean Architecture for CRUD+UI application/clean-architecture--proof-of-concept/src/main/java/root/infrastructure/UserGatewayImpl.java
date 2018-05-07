package root.infrastructure;

import java.util.Collection;
import javax.annotation.Resource;

import root.domain.User;
import root.application.UserGateway;

public class UserGatewayImpl implements UserGateway
{
    @Resource
    private UserRepository userRepository;
    
    @Override
    public void save(User user)
    {
        userRepository.save(user);
    }
    
    @Override
    public User findById(Integer id)
    {
        return userRepository.findOne(id);
    }
    
    @Override
    public Collection<User> findAll()
    {
        return userRepository.findAll();
    }
    
    @Override
    public void removeById(Integer id)
    {
        userRepository.delete(id);
    }
}
