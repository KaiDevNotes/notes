package main;

import DAO.Factory;
import java.util.List;
import entity.Continent;
import entity.Location;
import entity.Server;
import util.JPAUtility;


public class Main { 
    
    
    
    public static void main(String[] args)
    {        
        // СОЗДАЕМ ОБЪЕКТЫ-СУЩНОСТИ И СОХРАНИЯМ ИХ В БД
        
//        Continent continent1 = new Continent();
//        continent1.setName("North America");
//        continent1 = Factory.getInstance().getContinentDAO().save(continent1);
//        System.out.println("New Continent id = " + continent1.getId());
//        
//        Continent continent2 = new Continent();
//        continent2.setName("Europe");
//        continent2 = Factory.getInstance().getContinentDAO().save(continent2);
//        System.out.println("New Continent id = " + continent2.getId());
//        
//        Continent continent3 = new Continent();
//        continent3.setName("Australia");
//        continent3 = Factory.getInstance().getContinentDAO().save(continent3);
//        System.out.println("New Continent id = " + continent3.getId());
//        
//        Location location1 = new Location();
//        location1.setName("USA, New York");
//        location1.setContinent(continent1);
//        location1 = Factory.getInstance().getLocationDAO().save(location1);
//        System.out.println("New Location id = " + location1.getId());
//        
//        Location location2 = new Location();
//        location2.setName("Germany, Berlin");        
//        location2.setContinent(continent2);
//        location2 = Factory.getInstance().getLocationDAO().save(location2);
//        System.out.println("New Location id = " + location2.getId());
//        
//        Location location3 = new Location();
//        location3.setName("Australia, Canberra");
//        location3.setContinent(continent3);
//        location3 = Factory.getInstance().getLocationDAO().save(location3);
//        System.out.println("New Location id = " + location3.getId());
//        
//        Server server11 = new Server();
//        server11.setName("Server #1.1");
//        server11.setLocation(location1);
//        server11 = Factory.getInstance().getServerDAO().save(server11);
//        System.out.println("New Server id = " + server11.getId());
//        
//        Server server12 = new Server();
//        server12.setName("Server #1.2");
//        server12.setLocation(location1);
//        server12 = Factory.getInstance().getServerDAO().save(server12);
//        System.out.println("New Server id = " + server12.getId());
//        
//        Server server21 = new Server();
//        server21.setName("Server #2.1");
//        server21.setLocation(location2);
//        server21 = Factory.getInstance().getServerDAO().save(server21);
//        System.out.println("New Server id = " + server21.getId());
//        
//        Server server22 = new Server();
//        server22.setName("Server #2.2");
//        server22.setLocation(location2);
//        server22 = Factory.getInstance().getServerDAO().save(server22);
//        System.out.println("New Server id = " + server22.getId());
//        
//        Server server31 = new Server();
//        server31.setName("Server #3.1");
//        server31.setLocation(location3);
//        server31 = Factory.getInstance().getServerDAO().save(server31);
//        System.out.println("New Server id = " + server31.getId());
//        
//        Server server32 = new Server();
//        server32.setName("Server #3.2");
//        server32.setLocation(location3);
//        server32 = Factory.getInstance().getServerDAO().save(server32);
//        System.out.println("New Server id = " + server32.getId());   
        
        
        
        
        // ПОЛУЧАЕМ КОНТИНЕНТ (ПО ID) И СВЯЗАННЫЕ С НИМ ЛОКАЦИИ, И СВЯЗАННЫЕ С ЛОКАЦИЯМИ СЕРВЕРА
        
//        Continent continent = Factory.getInstance().getContinentDAO().getById(1);
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
        
//        Continent continent = Factory.getInstance().getContinentDAO().getByName("Europe");
//        int id = continent.getId();
//        String name = continent.getName();
//        System.out.println("\n---------------------------");
//        System.out.println("Continent --> ID: " + id + "; Name: " + name);
//        System.out.println("---------------------------\n");
                  
        
        
        
        // ПОЛУЧАЕМ ЛОКАЦИЮ (ПО ID), СВЯЗАННЫЙ С НЕЙ КОНТИНЕНТ И СВЯЗАННЫЕ С НЕЙ СЕРВЕРА
        
//        Location location = Factory.getInstance().getLocationDAO().getById(3);   
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
        
//        Server server = Factory.getInstance().getServerDAO().getById(3);  
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

        
        
        
        // Необходимо для закрытия соединения с БД 
        // и окончания потока выполнения программы.
        JPAUtility.close();
        
    }
}
