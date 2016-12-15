package DAO;

import java.sql.SQLException;
import java.util.List;

import entity.Continent;


public interface ContinentDAO {
    public Integer addContinent(Continent continent) throws SQLException;  
    public void updateContinent(Continent continent) throws SQLException;
    public Continent getContinentById(Integer id) throws SQLException;	 
    public List getAllContinents() throws SQLException;		
    public void deleteContinent(Continent continent) throws SQLException;
    
    public Continent getContinentByIdWithLocations(Integer id) throws SQLException;
    public Continent getContinentByIdWithLocationsAndServers(Integer id) throws SQLException;
}

