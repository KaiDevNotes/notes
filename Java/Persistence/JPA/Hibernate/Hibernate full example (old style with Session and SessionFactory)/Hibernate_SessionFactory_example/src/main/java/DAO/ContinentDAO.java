package DAO;

import entity.Continent;
import entity.Location;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;


public class ContinentDAO extends AbstractDAO<Continent> {
    
    public ContinentDAO(){
        super(Continent.class);
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
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } 
        finally {
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
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } 
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return continent;
    }
}

