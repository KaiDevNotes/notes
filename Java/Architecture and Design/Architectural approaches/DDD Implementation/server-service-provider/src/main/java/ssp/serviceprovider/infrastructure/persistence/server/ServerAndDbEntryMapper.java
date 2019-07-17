package ssp.serviceprovider.infrastructure.persistence.server;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ssp.common.infrastructure.persistence.AggregateAndDbEntryMapper;
import ssp.serviceprovider.domain.server.Server;
import ssp.serviceprovider.domain.server.ServerId;

@Component
public class ServerAndDbEntryMapper implements AggregateAndDbEntryMapper<Server, ServerId, ServerDbEntry>
{
    @Override
    public ServerDbEntry mapToDbEntry(Server server)
    {
        if (server == null) return null;
               
        return ServerDbEntry.builder()
            .id(UUID.fromString(server.getId().getValue()))
            .ip(server.getIp())
            .port(server.getPort())
            .location(server.getLocation())
            .status(server.getStatus())
            .build();
    }
    
    @Override
    public Server mapToAggregate(ServerDbEntry dbEntry)
    {
        if (dbEntry == null) return null;
        
        return Server.builder()
            .id(new ServerId(dbEntry.getId().toString()))
            .ip(dbEntry.getIp())
            .port(dbEntry.getPort())
            .location(dbEntry.getLocation())
            .status(dbEntry.getStatus())
            .build();
    }
}
