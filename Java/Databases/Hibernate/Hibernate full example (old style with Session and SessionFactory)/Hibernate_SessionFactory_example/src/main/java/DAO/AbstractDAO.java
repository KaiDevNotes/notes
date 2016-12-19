package DAO;

import entity.Identifiable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;


public abstract class AbstractDAO<T extends Identifiable> implements DAO<T> {
    
    protected Class<T> type;
    
    public AbstractDAO(Class<T> type)
    {
        this.type = type;
    }

    @Override
    public T getById(Integer id)
    {
        Session session = null;
        T entity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entity = (T) session.createCriteria(type).add(Restrictions.eq("id", id)).uniqueResult();
            if (entity == null){
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
        return entity;
    }
    
    
    @Override
    public List<T> getAll()
    {
        Session session = null;
        List<T> entities = new ArrayList<T>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String typeName = type.getSimpleName();
            entities = session.createQuery("SELECT e FROM " + typeName + " e").list();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } 
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }
    
    
    @Override
    public Integer save(T entity)
    {
        Session session = null;
        Integer id = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            id = (Integer) session.save(entity);
            session.getTransaction().commit();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } 
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return id;
    }
    
    
    @Override
    public void update(T entity)
    {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } 
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    
    @Override
    public void delete(T entity)
    {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "BD I/O Error", JOptionPane.OK_OPTION);
        } 
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
}
