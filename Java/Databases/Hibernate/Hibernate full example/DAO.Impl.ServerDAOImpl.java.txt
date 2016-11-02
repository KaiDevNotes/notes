package DAO.Impl;

import DAO.ServerDAO;
import entity.Location;
import entity.Server;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;


public class ServerDAOImpl implements ServerDAO {
    
    public Integer addServer(Server server){
        Session session = null;
        Integer id = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            id = (Integer) session.save(server);
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
    
    public void updateServer(Server server){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(server);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
    }
    
    public Server getServerById(Integer id){
        Session session = null;
        Server server = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            server = (Server) session.createCriteria(Server.class).add(Restrictions.eq("id", id)).uniqueResult();
            if (server == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return server;
    }
    
    public List getAllServers(){
        Session session = null;
        List<Server> servers = new ArrayList<Server>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            servers = session.createSQLQuery("SELECT * FROM servers").addEntity("server", Server.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return servers;
    }
    
    public void deleteServer(Server server){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(server);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
    }
    
    
    public Server getServerByIdWithLocation(Integer id){
        Session session = null;
        Server server = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Вариант 1: Получаем сущность СЕРВЕР по id 
            // и инициализируем сущностей ЛОКАЦИЯ связанную с ним 
            server = (Server) session.createCriteria(Server.class).add(Restrictions.eq("id", id)).uniqueResult();
            Hibernate.initialize(server.getLocation());
            // Вариант 2: Для получения СЕРВЕРА по id и связанной с ним ЛОКАЦИИ   
            // используем FetchMode.JOIN
            //server = (Server) session.createCriteria(Server.class).add(Restrictions.eq("id", id)).setFetchMode("location", FetchMode.JOIN).uniqueResult();
            if (server == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return server;
    }
    
    
    public Server getServerByIdWithLocationAndContinent(Integer id){
        Session session = null;
        Server server = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Вариант 1: Получаем сущность СЕРВЕР по id, 
            // инициализируем сущность ЛОКАЦИЯ связанную с СЕРВЕРОМ,
            // инициализируем сущность КОНТИНЕНТ связанную с ЛОКАЦИЕЙ
            server = (Server) session.createCriteria(Server.class).add(Restrictions.eq("id", id)).uniqueResult();
            Hibernate.initialize(server.getLocation());
            Location location = server.getLocation();
            Hibernate.initialize(location.getContinent());
            // ------
            if (server == null){
                JOptionPane.showMessageDialog(null, "Row not found !", "Error...", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                    session.close();
            }
        }
        return server;
    }
            
}
