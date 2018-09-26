package root.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import root.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> 
{    
}
