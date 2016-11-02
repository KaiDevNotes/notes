package DAO;

import java.sql.SQLException;
import java.util.List;

import entity.Server;


public interface ServerDAO {
    public Integer addServer(Server server) throws SQLException;  
    public void updateServer(Server server) throws SQLException;
    public Server getServerById(Integer id) throws SQLException;	 
    public List getAllServers() throws SQLException;		
    public void deleteServer(Server server) throws SQLException;
    
    public Server getServerByIdWithLocation(Integer id) throws SQLException;
    public Server getServerByIdWithLocationAndContinent(Integer id) throws SQLException;
}
