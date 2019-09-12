package ssp.serviceprovider.infrastructure.persistence.server;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ssp.common.infrastructure.persistence.AbstractAggregateJpaRepository;
import ssp.serviceprovider.domain.server.Server;
import ssp.serviceprovider.domain.server.ServerId;
import ssp.serviceprovider.domain.server.ServerRepository;

@Component
@RequiredArgsConstructor
public class ServerJpaRepository extends AbstractAggregateJpaRepository<Server, ServerId, ServerDbEntry> implements ServerRepository
{
    private final ServerDbEntryRepository dbEntryRepository;
    private final ServerAndDbEntryMapper mapper;
    
    @Override
    protected ServerDbEntryRepository getDbEntryRepository()
    {
        return dbEntryRepository;
    }
    
    @Override
    protected ServerAndDbEntryMapper getMapper()
    {
        return mapper;
    }
}
