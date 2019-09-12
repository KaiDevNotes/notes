package ssp.serviceprovider.infrastructure.persistence.serviceuser;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ssp.common.infrastructure.persistence.AggregateAndDbEntryMapper;
import ssp.serviceprovider.domain.server.ServerId;
import ssp.serviceprovider.domain.serviceuser.ServiceUser;
import ssp.serviceprovider.domain.serviceuser.ServiceUserId;

@Component
public class ServiceUserAndDbEntryMapper implements AggregateAndDbEntryMapper<ServiceUser, ServiceUserId, ServiceUserDbEntry>
{
    @Override
    public ServiceUserDbEntry mapToDbEntry(ServiceUser user)
    {
        if (user == null) return null;
               
        return ServiceUserDbEntry.builder()
            .id(UUID.fromString(user.getId().getValue()))
            .login(user.getLogin())
            .password(user.getPassword())
            .serverId(user.getServerId().getValue())
            .build();
    }
    
    @Override
    public ServiceUser mapToAggregate(ServiceUserDbEntry dbEntry)
    {
        if (dbEntry == null) return null;
        
        return ServiceUser.builder()
            .id(new ServiceUserId(dbEntry.getId().toString()))
            .login(dbEntry.getLogin())
            .password(dbEntry.getPassword())
            .serverId(new ServerId(dbEntry.getServerId()))
            .build();
    }
}
