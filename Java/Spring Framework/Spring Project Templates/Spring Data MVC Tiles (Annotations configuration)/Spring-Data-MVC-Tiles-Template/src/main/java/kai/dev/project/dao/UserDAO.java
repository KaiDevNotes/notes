package kai.dev.project.dao;

import javax.annotation.Resource;
import kai.dev.project.dao.repository.UserRepository;
import kai.dev.project.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UserDAO extends AbstractDAO<User> {

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
