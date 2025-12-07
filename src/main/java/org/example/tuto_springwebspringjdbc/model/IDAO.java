package org.example.tuto_springwebspringjdbc.model;

import java.util.List;

public interface IDAO<T> {
    public List<T> getAll();
    public T getById(int id);
    public void add(T item);
    public void update(T item);
    public void delete(String id);

}
