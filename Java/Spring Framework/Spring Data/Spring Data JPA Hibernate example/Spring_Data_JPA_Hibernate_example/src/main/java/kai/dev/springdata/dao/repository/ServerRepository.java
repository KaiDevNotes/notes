package kai.dev.springdata.dao.repository;

import java.util.List;
import kai.dev.springdata.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServerRepository extends JpaRepository<Server, Integer> {
    
    Server findByName(String name);
    List<Server> findAllByRam(Integer ram);    
    Server findByNameAndRam(String name, Integer ram);    
}
