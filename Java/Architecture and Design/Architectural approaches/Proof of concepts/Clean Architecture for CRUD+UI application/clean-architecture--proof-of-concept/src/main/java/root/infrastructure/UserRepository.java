package root.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import root.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{    
}
