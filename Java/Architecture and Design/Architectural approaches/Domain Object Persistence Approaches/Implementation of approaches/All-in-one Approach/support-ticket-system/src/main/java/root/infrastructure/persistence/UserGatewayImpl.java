package root.infrastructure.persistence;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import root.application.UserGateway;
import root.domain.User;

public class UserGatewayImpl extends AbstractDomainObjectGateway<User> implements UserGateway
{
    @Resource
    private UserRepository userRepository;
    
    @Override
    protected JpaRepository<User, UUID> getRepository()
    {
        return userRepository;
    }
}
