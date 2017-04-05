package core.dao;

import java.util.Collection;

import core.bean.Criteria;
import core.bean.Identifiable;

/**
 * 
 * @author rjaywant
 * 
 * @param <T>
 */
public interface Dao<T extends Identifiable> {

    T load(String id);

    void add(T item);

    void delete(T item);

    void update(T item);

    <C extends Collection<T>> C filter(Criteria<T> criteria, C results);

}
