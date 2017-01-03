package kai.dev.project.dao;

import java.util.List;
import kai.dev.project.entity.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractDAO<T extends Identifiable> {
    
    @Transactional
    public T getById(Integer id)
    {
        JpaRepository<T, Integer> entityRepository = getEntityRepository();
        T entity = entityRepository.findOne(id);
        return entity;
    }
    
    @Transactional
    public List<T> getAll()
    {
        JpaRepository<T, Integer> entityRepository = getEntityRepository();
        List<T> entities =  entityRepository.findAll();
        return entities;
    }
    
    @Transactional
    public T save(T entity)
    {
        JpaRepository<T, Integer> entityRepository = getEntityRepository();
        T savedEntity = entityRepository.saveAndFlush(entity);
        return savedEntity;
    }
    
    @Transactional
    public T update(T entity)
    {
        JpaRepository<T, Integer> entityRepository = getEntityRepository();
        T updatedEntity = entityRepository.saveAndFlush(entity);
        return updatedEntity;
    }
    
    @Transactional
    public void delete(T entity)
    {
        JpaRepository<T, Integer> entityRepository = getEntityRepository();
        entityRepository.delete(entity);
    }   
    
    //----------------------------------------------------------------
    
    abstract protected JpaRepository<T, Integer> getEntityRepository();
}
