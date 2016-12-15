package DAO.Impl;

import DAO.ContinentDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import entity.Continent;
import entity.Location;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import util.HibernateUtil;


public class ContinentDAOImpl implements ContinentDAO {
    
    public Integer addContinent(Continent continent){
        Session session = null;
        Integer id = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            id = (Integer) session.save(continent);
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
    
    public void updateContinent(Continent continent){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(continent);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
    }
    
    public Continent getContinentById(Integer id){
        Session session = null;
        Continent continent = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            continent = (Continent) session.createCriteria(Continent.class).add(Restrictions.eq("id", id)).uniqueResult();
            if (continent == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return continent;
    }
    
    public List getAllContinents(){
        Session session = null;
        List<Continent> continents = new ArrayList<Continent>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            continents = session.createSQLQuery("SELECT * FROM continents").addEntity("continent", Continent.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return continents;
    }
    
    public void deleteContinent(Continent continent){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(continent);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
    }
    
    
    public Continent getContinentByIdWithLocations(Integer id){
        Session session = null;
        Continent continent = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Вариант 1: Получаем сущность КОНТИНЕНТ по id 
            // и инициализируем коллекцию сущностей ЛОКАЦИЯ 
            continent = (Continent) session.createCriteria(Continent.class).add(Restrictions.eq("id", id)).uniqueResult();
            Hibernate.initialize(continent.getLocations());
            // Вариант 2: Для получения КОНТИНЕНТА по ID и связанных с ним ЛОКИЦИЙ 
            // используем FetchMode.JOIN
            //continent = (Continent) session.createCriteria(Continent.class).add(Restrictions.eq("id", id)).setFetchMode("locations", FetchMode.JOIN).uniqueResult();
            if (continent == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return continent;
    }
    
    
    public Continent getContinentByIdWithLocationsAndServers(Integer id){
        Session session = null;
        Continent continent = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Вариант 1: Получаем сущность КОНТИНЕНТ по id, 
            // инициализируем коллекцию сущностей ЛОКАЦИЯ сущности КОНТИНЕНТ,
            // инициализируем коллекцию сущностей СЕРВЕР каждой сущности ЛОКАЦИЯ
            continent = (Continent) session.createCriteria(Continent.class).add(Restrictions.eq("id", id)).uniqueResult();
            Hibernate.initialize(continent.getLocations());
            List<Location> locations = continent.getLocations();
            for (Location location : locations)
                Hibernate.initialize(location.getServers()); 
            // --------------    
            if (continent == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return continent;
    }
            
}
