package ssp.common.infrastructure.persistence;

import ssp.common.domain.Aggregate;
import ssp.common.domain.Identity;

public interface AggregateAndDbEntryMapper<A extends Aggregate<ID>, ID extends Identity, E>
{
    E mapToDbEntry(A aggregate);
    A mapToAggregate(E dbEntry);
}
