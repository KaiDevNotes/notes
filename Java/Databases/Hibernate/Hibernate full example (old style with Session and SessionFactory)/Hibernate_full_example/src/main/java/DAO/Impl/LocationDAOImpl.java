package DAO.Impl;

import DAO.LocationDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entity.Location;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;


public class LocationDAOImpl implements LocationDAO {
    
    public Integer addLocation(Location location){
        Session session = null;
        Integer id = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            id = (Integer) session.save(location);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return id;
    }
    
    public void updateLocation(Location location){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(location);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
    }
    
    public Location getLocationById(Integer id){
        Session session = null;
        Location location = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            location = (Location) session.createCriteria(Location.class).add(Restrictions.eq("id", id)).uniqueResult();
            if (location == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return location;
    }
    
    public List getAllLocations(){
        Session session = null;
        List<Location> locations = new ArrayList<Location>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            locations = session.createSQLQuery("SELECT * FROM locations").addEntity("location", Location.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return locations;
    }
    
    public void deleteLocation(Location location){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(location);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return location;
    }
            
}
