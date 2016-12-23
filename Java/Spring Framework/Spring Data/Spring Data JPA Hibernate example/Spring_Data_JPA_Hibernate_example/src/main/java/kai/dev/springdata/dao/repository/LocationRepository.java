package kai.dev.springdata.dao.repository;

import kai.dev.springdata.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationRepository extends JpaRepository<Location, Integer> {
    
    Location findByName(String name);   
}
