package core.service;

import java.util.List;
import java.util.Set;

import core.bean.Criteria;
import core.bo.Bo;

/**
 * 
 * @author rjaywant
 * 
 * @param <T>
 */
public interface BoService<T extends Bo> {

    T load(String id);

    void add(T item);

    void delete(T item);

    void update(T item);

    List<T> getList(Criteria<T> criteria);

    Set<T> getSet(Criteria<T> criteria);
}
