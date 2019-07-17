package ssp.serviceprovider.domain.servicecontroller;

import ssp.serviceprovider.domain.server.Server;
import ssp.serviceprovider.domain.serviceuser.ServiceUser;

public interface ServiceController
{
    void createUserOnServer(ServiceUser user, Server server);
    
    void deleteUserFromServer(ServiceUser user, Server server);
    
    void moveUserToAnotherServer(ServiceUser user, Server currentServer, Server targetServer);
}
