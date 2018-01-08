package root.user;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDAO extends AbstractDAO<User> 
{
    @Resource
    private UserRepository userRepository;
    
    @Transactional
    public User getByLoginAndPassword(String login, String password) 
    {
        return userRepository.findByLoginAndPassword(login, password);
    } 
    
    //--------------------------------------------------------
    
    @Override
    protected UserRepository getEntityRepository()
    {
        return userRepository;
    }    
}
