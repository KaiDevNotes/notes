package kai.dev.project.dao.repository;

import kai.dev.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByLoginAndPassword(String login, String password);
    
}
