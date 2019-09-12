package ssp.serviceprovider.infrastructure.persistence.server;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerDbEntryRepository extends JpaRepository<ServerDbEntry, UUID>
{
}
