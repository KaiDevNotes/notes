package main;

import java.util.List;
import kai.dev.springdata.dao.ContinentDAO;
import kai.dev.springdata.dao.DAOFactory;
import kai.dev.springdata.dao.ServerDAO;
import kai.dev.springdata.entity.Continent;
import kai.dev.springdata.entity.Location;
import kai.dev.springdata.entity.Server;
import kai.dev.springdata.exception.EntityNotFound;


public class Main {

    public static void main(String[] args) throws EntityNotFound
    {            
        // СОЗДАЕМ ОБЪЕКТЫ-СУЩНОСТИ И СОХРАНИЯМ ИХ В БД
        
//        Continent continent1 = new Continent("North America");
//        continent1 = DAOFactory.INSTANCE.getContinentDAO().save(continent1);
//        System.out.println("New Continent id = " + continent1.getId());
//        
//        Continent continent2 = new Continent("Europe");
//        continent2 = DAOFactory.INSTANCE.getContinentDAO().save(continent2);
//        System.out.println("New Continent id = " + continent2.getId());
//        
//        Continent continent3 = new Continent("Australia");
//        continent3 = DAOFactory.INSTANCE.getContinentDAO().save(continent3);
//        System.out.println("New Continent id = " + continent3.getId());
//        
//        Location location1 = new Location("USA, New York");
//        location1.setContinent(continent1);
//        location1 = DAOFactory.INSTANCE.getLocationDAO().save(location1);
//        System.out.println("New Location id = " + location1.getId());
//        
//        Location location2 = new Location("Germany, Berlin");
//        location2.setContinent(continent2);
//        location2 = DAOFactory.INSTANCE.getLocationDAO().save(location2);
//        System.out.println("New Location id = " + location2.getId());
//        
//        Location location3 = new Location("Australia, Canberra");
//        location3.setContinent(continent3);
//        location3 = DAOFactory.INSTANCE.getLocationDAO().save(location3);
//        System.out.println("New Location id = " + location3.getId());
//        
//        Server server11 = new Server("Server #1.1", 1024);
//        server11.setLocation(location1);
//        server11 = DAOFactory.INSTANCE.getServerDAO().save(server11);
//        System.out.println("New Server id = " + server11.getId());
//        
//        Server server12 = new Server("Server #1.2", 2048);
//        server12.setLocation(location1);
//        server12 = DAOFactory.INSTANCE.getServerDAO().save(server12);
//        System.out.println("New Server id = " + server12.getId());
//        
//        Server server21 = new Server("Server #2.1", 2048);
//        server21.setLocation(location2);
//        server21 = DAOFactory.INSTANCE.getServerDAO().save(server21);
//        System.out.println("New Server id = " + server21.getId());
//        
//        Server server22 = new Server("Server #2.2", 1024);
//        server22.setLocation(location2);
//        server22 = DAOFactory.INSTANCE.getServerDAO().save(server22);
//        System.out.println("New Server id = " + server22.getId());
//        
//        Server server31 = new Server("Server #3.1", 1024);
//        server31.setLocation(location3);
//        server31 = DAOFactory.INSTANCE.getServerDAO().save(server31);
//        System.out.println("New Server id = " + server31.getId());
//        
//        Server server32 = new Server("Server #3.2", 2048);
//        server32.setLocation(location3);
//        server32 = DAOFactory.INSTANCE.getServerDAO().save(server32);
//        System.out.println("New Server id = " + server32.getId()); 
        
        
        
        
        // ПОЛУЧАЕМ КОНТИНЕНТ (ПО ID) И СВЯЗАННЫЕ С НИМ ЛОКАЦИИ, И СВЯЗАННЫЕ С ЛОКАЦИЯМИ СЕРВЕРА
        
//        Continent continent = DAOFactory.INSTANCE.getContinentDAO().getByIdWithLocationsAndServers(1);
//        String continentName = continent.getName();
//        
//        System.out.println("\n---------------------------");
//        System.out.println("Continent: " + continentName);
//        
//        List<Location> locations = continent.getLocations();
//        for (Location location : locations)
//        {
//            String locationName = location.getName();
//            System.out.println("    Location: " + locationName);
//            
//            List<Server> servers = location.getServers();
//            for (Server server : servers)
//            {
//                String serverName = server.getName();
//                System.out.println("        Server: " + serverName);
//            }
//        }
//        System.out.println("---------------------------\n");
        
        
        
        
        // ПОЛУЧАЕМ КОНТИНЕНТ (ПО ИМЕНИ)
        
//        Continent continent = DAOFactory.INSTANCE.getContinentDAO().getByName("Europe");
//        int id = continent.getId();
//        String name = continent.getName();
//        System.out.println("\n---------------------------");
//        System.out.println("Continent --> ID: " + id + "; Name: " + name);
//        System.out.println("---------------------------\n");
                  
        
        
        
        // ПОЛУЧАЕМ ЛОКАЦИЮ (ПО ID), СВЯЗАННЫЙ С НЕЙ КОНТИНЕНТ И СВЯЗАННЫЕ С НЕЙ СЕРВЕРА
        
//        Location location = DAOFactory.INSTANCE.getLocationDAO().getByIdWithContinentAndServers(3);   
//        String locationName = location.getName();
//        
//        Continent continent = location.getContinent();  
//        String continentName = continent.getName();        
//        
//        System.out.println("\n---------------------------");      
//        System.out.println("Continent: " + continentName);
//        System.out.println("    Location: " + locationName);
//        
//        List<Server> servers = location.getServers();
//        for (Server server : servers)
//        {
//            String serverName = server.getName();
//            System.out.println("        Server: " + serverName);
//        }
//        System.out.println("---------------------------\n");

        
        
        
        // ПОЛУЧАЕМ СЕРВЕР (ПО ID) И СВЯЗАННУЮ С НИМ ЛОКАЦИЮ И КОНТИНЕНТ
        
//        Server server = DAOFactory.INSTANCE.getServerDAO().getByIdWithLocationAndContinent(2);  
//        String serverName = server.getName();
//        
//        Location location = server.getLocation();   
//        String locationName = location.getName();
//        
//        Continent continent = location.getContinent();  
//        String continentName = continent.getName();
//        
//        System.out.println("\n---------------------------");
//        System.out.println("Continent: " + continentName);         
//        System.out.println("    Location: " + locationName);
//        System.out.println("        Server: " + serverName);
//        System.out.println("---------------------------\n");        
    }    
}
