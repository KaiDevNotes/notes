package ssp.order.infrastructure.persistence.subscriptionplan;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanDbEntryRepository extends JpaRepository<SubscriptionPlanDbEntry, UUID>
{
}
