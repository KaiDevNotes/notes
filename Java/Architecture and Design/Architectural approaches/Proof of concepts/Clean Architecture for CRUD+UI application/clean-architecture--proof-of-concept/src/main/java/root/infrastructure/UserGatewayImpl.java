package root.infrastructure;

import java.util.Collection;
import java.util.stream.Collectors;
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
        userRepository.save(new UserRow(user));
    }
    
    @Override
    public User findById(Integer id)
    {
        return userRepository.findOne(id).toDomainObject();
    }
    
    @Override
    public Collection<User> findAll()
    {
        return userRepository.findAll()
            .stream()
            .map(userRow -> userRow.toDomainObject())
            .collect(Collectors.toList());
    }
    
    @Override
    public void removeById(Integer id)
    {
        userRepository.delete(id);
    }
}
