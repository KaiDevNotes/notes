package kai.dev.springdata.dao.repository;

import kai.dev.springdata.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ContinentRepository extends JpaRepository<Continent, Integer> {
    
    Continent findByName(String name);  
    
    @Query("SELECT c FROM Continent c JOIN FETCH c.locations WHERE c.id = :id")
    Continent findByIdWithLocations(@Param("id") Integer id);
}
