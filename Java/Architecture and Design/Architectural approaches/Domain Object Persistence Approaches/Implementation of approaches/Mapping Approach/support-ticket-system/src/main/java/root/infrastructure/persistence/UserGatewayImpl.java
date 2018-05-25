package root.infrastructure.persistence;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import root.application.UserGateway;
import root.domain.User;

@Component
public class UserGatewayImpl 
    extends AbstractGateway<User, UserDbEntry> 
    implements UserGateway
{
    @Resource
    private UserRepository userRepository;
    
    @Autowired
    private UserAndDbEntityMapper mapper;
    
    @Override
    protected JpaRepository getRepository()
    {
        return userRepository;
    }
    
    @Override
    protected DomainObjectAndDbEntityMapper getMapper()
    {
        return mapper;
    }
}
