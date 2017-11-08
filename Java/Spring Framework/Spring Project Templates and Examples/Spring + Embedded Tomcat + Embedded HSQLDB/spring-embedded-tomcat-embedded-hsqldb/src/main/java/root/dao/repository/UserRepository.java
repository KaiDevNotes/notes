package root.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import root.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{    
    User findByLoginAndPassword(String login, String password);    
}
