package ohtu.dao;

import java.util.List;

/**
 * a generic dao interface
 * @param <T> the type of the database object
 * @param <K> type of database primary key, probably Integer
 */
public interface Dao<T, K> {

    T create(T object);

    T read(K key);

    T update(T object);

    boolean delete(K key);

    List<T> list();
}
