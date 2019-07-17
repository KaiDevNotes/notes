package ssp.order.infrastructure.persistence.serviceorder;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOrderDbEntryRepository extends JpaRepository<ServiceOrderDbEntry, UUID>
{
}
