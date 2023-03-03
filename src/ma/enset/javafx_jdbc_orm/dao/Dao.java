package ma.enset.javafx_jdbc_orm.dao;

import java.util.List;

public interface Dao <T>{
    T find(long id);
    List<T> findAll();
    void save(T o);
    void delete(T o);
    void update(T o);
}
