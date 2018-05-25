package root.infrastructure.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import root.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> 
{    
}
