package root.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> 
{    
    User findByLoginAndPassword(String login, String password);    
}
