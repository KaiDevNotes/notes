package DAO;

import java.sql.SQLException;
import java.util.List;

import entity.Location;


public interface LocationDAO {
    public Integer addLocation(Location location) throws SQLException;  
    public void updateLocation(Location location) throws SQLException;
    public Location getLocationById(Integer id) throws SQLException;	 
    public List getAllLocations() throws SQLException;		
    public void deleteLocation(Location location) throws SQLException;
    
    public Location getLocationByIdWithServers(Integer id) throws SQLException;
    public Location getLocationByIdWithServersAndContinent(Integer id) throws SQLException;
}

