package root.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRow, Integer> 
{    
}
