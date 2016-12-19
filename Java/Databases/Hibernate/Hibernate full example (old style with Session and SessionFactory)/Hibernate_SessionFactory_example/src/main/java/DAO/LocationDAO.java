package DAO;

import entity.Location;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;


public class LocationDAO extends AbstractDAO<Location> {
    
    public LocationDAO(){
        super(Location.class);
    }
    
    public Location getLocationByIdWithServers(Integer id){
        Session session = null;
        Location location = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Вариант 1: Получаем сущность ЛОКАЦИЯ по id 
            // и инициализируем коллекцию сущностей СЕРВЕР 
            location = (Location) session.createCriteria(Location.class).add(Restrictions.eq("id", id)).uniqueResult();
            Hibernate.initialize(location.getServers());
            // Вариант 2: Для получения ЛОКАЦИИ и связанных с ней СЕРВЕРОВ 
            // используем FetchMode.JOIN
            //location = (Location) session.createCriteria(Location.class).add(Restrictions.eq("id", id)).setFetchMode("servers", FetchMode.JOIN).uniqueResult();
            if (location == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } 
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return location;
    }
    
    
    public Location getLocationByIdWithServersAndContinent(Integer id){
        Session session = null;
        Location location = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Вариант 1: Получаем сущность ЛОКАЦИЯ по id, 
            // инициализируем коллекцию сущностей СЕРВЕР 
            // инициализируем сущность КОНТИНЕНТ, кот. связан с данной ЛОКАЦИЕЙ
            location = (Location) session.createCriteria(Location.class).add(Restrictions.eq("id", id)).uniqueResult();
            Hibernate.initialize(location.getServers());
            Hibernate.initialize(location.getContinent());
            // Вариант 2: Для получения ЛОКАЦИИ и связанного с ней КОНТИНЕНТА и СЕРВЕРОВ 
            // используем FetchMode.JOIN
            //location = (Location) session.createCriteria(Location.class).add(Restrictions.eq("id", id)).setFetchMode("servers", FetchMode.JOIN).setFetchMode("continent", FetchMode.JOIN).uniqueResult();
            if (location == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } 
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return location;
    }
}

