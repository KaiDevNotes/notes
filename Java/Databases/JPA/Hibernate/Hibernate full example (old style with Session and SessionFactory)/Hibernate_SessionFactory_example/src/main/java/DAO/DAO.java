package DAO;

import java.util.List;


public interface DAO<T> {

    T getById(Integer id);
    List<T> getAll();
    Integer save(T entity);
    void update(T entity);
    void delete(T entity);
    
}
