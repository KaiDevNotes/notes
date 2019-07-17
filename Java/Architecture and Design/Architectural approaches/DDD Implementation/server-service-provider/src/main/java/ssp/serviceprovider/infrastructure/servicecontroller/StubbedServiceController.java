package ssp.serviceprovider.infrastructure.servicecontroller;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ssp.serviceprovider.domain.server.Server;
import ssp.serviceprovider.domain.servicecontroller.AbstractServiceController;
import ssp.serviceprovider.domain.serviceuser.ServiceUser;

@Component
@Slf4j
public class StubbedServiceController extends AbstractServiceController
{    
    @Override
    protected void create(ServiceUser user, Server server)
    {
        log.info("[ServiceProvider Core Domain]: User [{}] was created on server [{} -- {}]", 
            user.getLogin(), server.getId().getValue(), server.getLocation().name());
    }

    @Override
    protected void delete(ServiceUser user, Server server)
    {
        log.info("[ServiceProvider Core Domain]: User [{}] was deleted from server [{} -- {}]", 
            user.getLogin(), server.getId().getValue(), server.getLocation().name());
    }
}
