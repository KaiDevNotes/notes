package root.infrastructure.persistence;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import root.application.UserGateway;
import root.domain.User;

public class UserGatewayImpl 
    extends AbstractDomainObjectGateway<User, UserDbEntry> 
    implements UserGateway
{
    @Resource
    private UserRepository userRepository;
    
    @Autowired
    private UserAndDbEntryMapper mapper;
    
    @Override
    protected UserRepository getRepository()
    {
        return userRepository;
    }
    
    @Override
    protected UserAndDbEntryMapper getMapper()
    {
        return mapper;
    }
}
