package ssp.serviceprovider.application;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.common.application.event.EventBus;
import ssp.common.application.event.serviceuser.ServiceUserCreated;
import ssp.common.application.event.serviceuser.ServiceUserDeleted;
import ssp.common.application.event.serviceuser.ServiceUserMoved;
import ssp.serviceprovider.application.command.CreateServiceUser;
import ssp.serviceprovider.application.command.DeleteServiceUser;
import ssp.serviceprovider.application.command.MoveServiceUser;
import ssp.serviceprovider.domain.server.Server;
import ssp.serviceprovider.domain.server.ServerId;
import ssp.serviceprovider.domain.server.ServerRepository;
import ssp.serviceprovider.domain.servicecontroller.ServiceController;
import ssp.serviceprovider.domain.serviceuser.ServiceUser;
import ssp.serviceprovider.domain.serviceuser.ServiceUserId;
import ssp.serviceprovider.domain.serviceuser.ServiceUserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceProviderService
{
    private static final String SERVER_NOT_FOUND_MSG = "Server with id [%s] does not exist.";
    private static final String SERVICE_USER_NOT_FOUND_MSG = "ServiceUser with id [%s] does not exist.";
    
    private static final String LOGIN_PREFIX = "USR-";
    private static final String PASSWORD_PREFIX = "PWD-";
    private static final String UUID_SECTIONS_SEPARATOR = "-";
    private static final int UUID_LAST_SECTION_INDEX = 4;
    
    private final ServerRepository serverRepository;
    private final ServiceUserRepository userRepository;
    private final ServiceController serviceController;
    private final EventBus eventBus;
    
    @Transactional
    public void createNewUser(CreateServiceUser command)
    {
        log.info("[ServiceProvider Core Domain]: CreateServiceUser command was received");
        
        Server server = serverRepository.findById(new ServerId(command.getServerId()))
            .orElseThrow(() -> new RuntimeException(String.format(SERVER_NOT_FOUND_MSG, command.getServerId())));
        
        ServiceUser user = ServiceUser.builder()
                                      .login(generateLogin())
                                      .password(generatePassword())
                                      .build();
        
        serviceController.createUserOnServer(user, server);
        
        userRepository.save(user);
        
        log.info("[ServiceProvider Core Domain]: ServiceUserCreated event will be published");
        
        eventBus.publish(
            ServiceUserCreated.builder()
                .orderId(command.getOrderId())
                .serviceUserId(user.getId().getValue())
                .serverId(server.getId().getValue())
                .build());
    }
    
    @Transactional
    public void deleteUser(DeleteServiceUser command)
    {
        log.info("[ServiceProvider Core Domain]: DeleteServiceUser command was received");
        
        ServiceUser user = userRepository.findById(new ServiceUserId(command.getServiceUserId()))
            .orElseThrow(() -> new RuntimeException(String.format(SERVICE_USER_NOT_FOUND_MSG, command.getServiceUserId())));
        
        Server server = serverRepository.findById(user.getServerId())
            .orElseThrow(() -> new RuntimeException(String.format(SERVER_NOT_FOUND_MSG, user.getServerId().getValue())));
        
        serviceController.deleteUserFromServer(user, server);
        
        userRepository.delete(user);
        
        log.info("[ServiceProvider Core Domain]: ServiceUserDeleted event will be published");
        
        eventBus.publish(
            new ServiceUserDeleted(user.getId().getValue()));
    }
    
    @Transactional
    public void moveUserToAnotherServer(MoveServiceUser command)
    {
        log.info("[ServiceProvider Core Domain]: MoveServiceUser command was received");
        
        ServiceUser user = userRepository.findById(new ServiceUserId(command.getServiceUserId()))
            .orElseThrow(() -> new RuntimeException(String.format(SERVICE_USER_NOT_FOUND_MSG, command.getServiceUserId())));
            
        Server currentServer = serverRepository.findById(new ServerId(command.getCurrentServerId()))
            .orElseThrow(() -> new RuntimeException(String.format(SERVER_NOT_FOUND_MSG, command.getCurrentServerId())));
        
        Server targetServer = serverRepository.findById(new ServerId(command.getTargetServerId()))
            .orElseThrow(() -> new RuntimeException(String.format(SERVER_NOT_FOUND_MSG, command.getTargetServerId())));
        
        serviceController.moveUserToAnotherServer(user, currentServer, targetServer);
        
        userRepository.save(user);
        
        log.info("[ServiceProvider Core Domain]: ServiceUserMoved event will be published");
        
        eventBus.publish(
            ServiceUserMoved.builder()
                .userId(user.getId().getValue())
                .previousServerId(currentServer.getId().getValue())
                .currentServerId(targetServer.getId().getValue())
                .build());
    }
    
    private String generateLogin()
    {
        return LOGIN_PREFIX + getUUIDLastSection();
    }
    
    private String generatePassword()
    {
        return PASSWORD_PREFIX + getUUIDLastSection();
    }
    
    private String getUUIDLastSection()
    {
        String uuid = UUID.randomUUID().toString();
        return uuid.split(UUID_SECTIONS_SEPARATOR)[UUID_LAST_SECTION_INDEX].toUpperCase();
    }
}
