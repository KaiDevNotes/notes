package root.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDAO<T extends Identifiable> 
{    
    @Transactional
    public T getById(Integer id)
    {
        return getEntityRepository().findOne(id);
    }
    
    @Transactional
    public List<T> getAll()
    {
        return getEntityRepository().findAll();
    }
    
    @Transactional
    public T save(T entity)
    {
        return getEntityRepository().saveAndFlush(entity);
    }
    
    @Transactional
    public T update(T entity)
    {
        return getEntityRepository().saveAndFlush(entity);
    }
    
    @Transactional
    public void delete(T entity)
    {
        getEntityRepository().delete(entity);
    }   
    
    //----------------------------------------------------------------
    
    abstract protected JpaRepository<T, Integer> getEntityRepository();
}
