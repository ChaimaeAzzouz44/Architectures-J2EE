package org.example.getsionecolefeliere.Dao;

import java.io.Serializable;
import java.util.List;

public interface ICRUD<T> {
    void create(T entity);
    void update(T entity);
    void delete(Serializable id);
    T findById(Serializable id);
    List<T> findAll();
}