package ssp.account.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDbEntryRepository extends JpaRepository<AccountDbEntry, UUID>
{
}
