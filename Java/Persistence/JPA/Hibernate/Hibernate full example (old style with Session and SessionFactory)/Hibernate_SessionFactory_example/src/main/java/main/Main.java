package main;

import DAO.Factory;
import java.sql.SQLException;
import java.util.List;
import entity.Continent;
import entity.Location;
import entity.Server;


public class Main { 
    
    public static void main(String[] args) throws SQLException 
    {        
        // СОЗДАЕМ ОБЪЕКТЫ-СУЩНОСТИ И СОХРАНИЯМ ИХ В БД
        
//        Continent continent1 = new Continent();
//        continent1.setName("North America");
//        
//        Continent continent2 = new Continent();
//        continent2.setName("Europe");
//        
//        Continent continent3 = new Continent();
//        continent3.setName("Australia");
//        
//        Factory.getInstance().getContinentDAO().save(continent1);
//        Factory.getInstance().getContinentDAO().save(continent2);
//        Factory.getInstance().getContinentDAO().save(continent3);
//        
//        Location location1 = new Location();
//        location1.setName("USA, New York");
//        location1.setContinent(continent1);
//        
//        Location location2 = new Location();
//        location2.setName("Germany, Berlin");        
//        location2.setContinent(continent2);
//        
//        Location location3 = new Location();
//        location3.setName("Australia, Canberra");
//        location3.setContinent(continent3);
//        
//        Factory.getInstance().getLocationDAO().save(location1);
//        Factory.getInstance().getLocationDAO().save(location2);
//        Factory.getInstance().getLocationDAO().save(location3);
//        
//        Server server11 = new Server();
//        server11.setName("Server #1.1");
//        server11.setLocation(location1);
//        
//        Server server12 = new Server();
//        server12.setName("Server #1.2");
//        server12.setLocation(location1);
//        
//        Server server21 = new Server();
//        server21.setName("Server #2.1");
//        server21.setLocation(location2);
//        
//        Server server22 = new Server();
//        server22.setName("Server #2.2");
//        server22.setLocation(location2);
//        
//        Server server31 = new Server();
//        server31.setName("Server #3.1");
//        server31.setLocation(location3);
//        
//        Server server32 = new Server();
//        server32.setName("Server #3.2");
//        server32.setLocation(location3);
//        
//        Factory.getInstance().getServerDAO().save(server11);
//        Factory.getInstance().getServerDAO().save(server12);
//        Factory.getInstance().getServerDAO().save(server21);
//        Factory.getInstance().getServerDAO().save(server22);
//        Factory.getInstance().getServerDAO().save(server31);
//        Factory.getInstance().getServerDAO().save(server32);        
        
   
        
        
        // ПОЛУЧАЕМ КОНТИНЕНТ (ПО ID) И СВЯЗАННЫЕ С НИМ ЛОКАЦИИ
        
//        Continent continent = Factory.getInstance().getContinentDAO().getContinentByIdWithLocations(1);
//        System.out.println("---------------------------");
//        System.out.println("Continent: "+continent.getName());
//        List<Location> locations = continent.getLocations();
//        for (Location location : locations){
//            System.out.println("    Location: "+location.getName());
//        }
//        System.out.println("---------------------------");
        
        
        
        
        // ПОЛУЧАЕМ КОНТИНЕНТ (ПО ID) И СВЯЗАННЫЕ С НИМ ЛОКАЦИИ, И СВЯЗАННЫЕ С ЛОКАЦИЯМИ СЕРВЕРА
        
//        Continent continent = Factory.getInstance().getContinentDAO().getContinentByIdWithLocationsAndServers(1);
//        System.out.println("---------------------------");
//        System.out.println("Continent: "+continent.getName());
//        List<Location> locations = continent.getLocations();
//        for (Location location : locations){
//            System.out.println("    Location: "+location.getName());
//            List<Server> servers = location.getServers();
//            for (Server server : servers){
//                System.out.println("        Server: "+server.getName());
//            }
//        }
//        System.out.println("---------------------------");
                  
        
        
        
        // ПОЛУЧАЕМ ЛОКАЦИЮ (ПО ID), СВЯЗАННЫЙ С НЕЙ КОНТИНЕНТ И СВЯЗАННЫЕ С НЕЙ СЕРВЕРА
        
//        Location location = Factory.getInstance().getLocationDAO().getLocationByIdWithServersAndContinent(2);        
//        System.out.println("---------------------------");      
//        System.out.println("Continent: "+location.getContinent().getName());
//        System.out.println("    Location: "+location.getName());
//        List<Server> servers = location.getServers();
//        for (Server server : servers){
//            System.out.println("        Server: "+server.getName());
//        }
//        System.out.println("---------------------------");
        
        
        
        
        // ПОЛУЧАЕМ СЕРВЕР (ПО ID) И СВЯЗАННУЮ С НИМ ЛОКАЦИЮ
        
//        Server server = Factory.getInstance().getServerDAO().getServerByIdWithLocation(3);
//        System.out.println("---------------------------");      
//        System.out.println("Location: "+server.getLocation().getName());
//        System.out.println("    Server: "+server.getName());
//        System.out.println("---------------------------");   
        
        
        
        
        // ПОЛУЧАЕМ СЕРВЕР (ПО ID) И СВЯЗАННУЮ С НИМ ЛОКАЦИЮ И КОНТИНЕНТ
        
//        Server server = Factory.getInstance().getServerDAO().getServerByIdWithLocationAndContinent(3);
//        System.out.println("---------------------------");
//        System.out.println("Continent: "+server.getLocation().getContinent().getName());
//        System.out.println("    Location: "+server.getLocation().getName());
//        System.out.println("        Server: "+server.getName());
//        System.out.println("---------------------------");
        
    }
}
