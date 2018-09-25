package root.infrastructure.persistence;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

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
    protected JpaRepository<UserDbEntry, UUID> getRepository()
    {
        return userRepository;
    }
    
    @Override
    protected DomainObjectAndDbEntryMapper<User, UserDbEntry> getMapper()
    {
        return mapper;
    }
}
