package ssp.serviceprovider.infrastructure.persistence.serviceuser;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceUserDbEntryRepository extends JpaRepository<ServiceUserDbEntry, UUID>
{
}
