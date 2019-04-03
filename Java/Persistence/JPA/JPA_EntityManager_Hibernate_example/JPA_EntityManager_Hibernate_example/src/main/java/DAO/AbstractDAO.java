package DAO;

import entity.Identifiable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JPAUtility;


public abstract class AbstractDAO<T extends Identifiable> implements DAO<T> {
    
    protected EntityManager entityManager;
    protected Class<T> type;
    
    public AbstractDAO(Class<T> type)
    {
        entityManager = JPAUtility.getEntityManager();
        this.type = type;
    }

    @Override
    public T getById(Integer id)
    {
        T entity = entityManager.find(type, id);
        return entity;
    }
    
    @Override
    public List<T> getAll()
    {
        String typeName = type.getSimpleName();
        Query query = entityManager.createQuery("SELECT e FROM " + typeName + " e");
        List<T> entities =  query.getResultList();
        return entities;
    }
    
    @Override
    public T save(T entity)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        // We can get Id of saved (added to persistance context) only after 
        // calling flush() method
        entityManager.flush();
        entityManager.getTransaction().commit();
        return entity;
    }
    
    @Override
    public T update(T entity)
    {
        entityManager.getTransaction().begin();
        T updatedEntity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return updatedEntity;
    }
    
    @Override
    public void delete(T entity)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
    
}
