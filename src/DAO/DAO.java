package DAO;

import java.util.List;

public interface DAO<T> {
    void add(T t);
    List<T> getAll();
    void update(T t);
    void delete(int id);
}
