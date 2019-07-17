package ssp.serviceprovider.domain.servicecontroller;

import ssp.serviceprovider.domain.server.Server;
import ssp.serviceprovider.domain.serviceuser.ServiceUser;

public abstract class AbstractServiceController implements ServiceController
{
    private static final String ASSIGNMENT_ERROR_MSG = "ServiceUser cannot be assigned to INACTIVE server";
    
    @Override
    public void createUserOnServer(ServiceUser user, Server server)
    {
        if (server.isNotActive())
        {
            throw new RuntimeException(ASSIGNMENT_ERROR_MSG);
        }
        
        user.assignToServer(server.getId());
        create(user, server);
    }
    
    @Override
    public void deleteUserFromServer(ServiceUser user, Server server)
    {
        user.unassignFromServer(server.getId());
        delete(user, server);
    }
    
    @Override
    public void moveUserToAnotherServer(ServiceUser user, Server currentServer, Server targetServer)
    {
        if (targetServer.isNotActive())
        {
            throw new RuntimeException(ASSIGNMENT_ERROR_MSG);
        }
        
        user.unassignFromServer(currentServer.getId());
        delete(user, currentServer);
        
        user.assignToServer(targetServer.getId());
        create(user, targetServer);
    }
    
    protected abstract void create(ServiceUser user, Server server);
    
    protected abstract void delete(ServiceUser user, Server server);
}
