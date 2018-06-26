package root.infrastructure.persistence;

import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import root.application.UserGateway;
import root.domain.User;

public class UserGatewayImpl extends AbstractDomainObjectGateway<User> implements UserGateway
{
    @Resource
    private UserRepository userRepository;
    
    @Override
    protected JpaRepository getRepository()
    {
        return userRepository;
    }
}
