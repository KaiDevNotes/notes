package root.dao;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import root.dao.repository.UserRepository;
import root.entity.User;

@Component
public class UserDAO extends AbstractDAO<User> 
{
    @Resource
    private UserRepository userRepository;
    
    @Transactional
    public User getByLoginAndPassword(String login, String password) 
    {
        User user = userRepository.findByLoginAndPassword(login, password);
        return user;
    } 
    
    //--------------------------------------------------------
    
    @Override
    protected UserRepository getEntityRepository()
    {
        return userRepository;
    }    
}
