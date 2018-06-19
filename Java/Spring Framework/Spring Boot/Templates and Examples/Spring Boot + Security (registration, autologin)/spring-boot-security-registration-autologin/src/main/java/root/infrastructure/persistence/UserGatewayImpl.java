package root.infrastructure.persistence;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import root.application.UserGateway;
import root.domain.User;

@Component
public class UserGatewayImpl 
    extends AbstractDomainObjectGateway<User, UserDbEntry> 
    implements UserGateway
{
    @Resource
    private UserRepository userRepository;
    
    @Autowired
    private UserAndDbEntryMapper userAndDbEntryMapper;
    
    @Override
    public User findByLogin(String login)
    {
        UserDbEntry userDbEntry = userRepository.findByLogin(login);
        return userAndDbEntryMapper.mapToDomainObject(userDbEntry);
    }
    
    
    //-------------------------------------------------
    
    
    @Override
    protected UserRepository getRepository()
    {
        return userRepository;
    }
    
    @Override
    protected DomainObjectAndDbEntryMapper getMapper()
    {
        return userAndDbEntryMapper;
    }
}
