package DAO;

import java.util.List;


public interface DAO<T> {

    T getById(Integer id);
    List<T> getAll();
    T save(T entity);
    T update(T entity);
    void delete(T entity);
    
}
