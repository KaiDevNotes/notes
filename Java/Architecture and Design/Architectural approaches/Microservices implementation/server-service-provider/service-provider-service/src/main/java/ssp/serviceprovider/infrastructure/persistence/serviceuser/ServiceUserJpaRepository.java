package ssp.serviceprovider.infrastructure.persistence.serviceuser;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ssp.common.infrastructure.persistence.AbstractAggregateJpaRepository;
import ssp.serviceprovider.domain.serviceuser.ServiceUser;
import ssp.serviceprovider.domain.serviceuser.ServiceUserId;
import ssp.serviceprovider.domain.serviceuser.ServiceUserRepository;

@Component
@RequiredArgsConstructor
public class ServiceUserJpaRepository extends AbstractAggregateJpaRepository<ServiceUser, ServiceUserId, ServiceUserDbEntry> implements ServiceUserRepository
{
    private final ServiceUserDbEntryRepository dbEntryRepository;
    private final ServiceUserAndDbEntryMapper mapper;
    
    @Override
    protected ServiceUserDbEntryRepository getDbEntryRepository()
    {
        return dbEntryRepository;
    }
    
    @Override
    protected ServiceUserAndDbEntryMapper getMapper()
    {
        return mapper;
    }
}
