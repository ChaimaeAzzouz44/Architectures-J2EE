package org.example.projet_hebirnet.Model;

import java.util.List;

public interface IDao<T> {
    boolean create(T obj);
    boolean update(T obj);
    boolean delete(String code);
    T findByCode(String code);
    List<T> findAll();
}