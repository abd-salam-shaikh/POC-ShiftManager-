package core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.bean.Criteria;
import core.bo.Bo;
import core.dao.Dao;

/**
 * 
 * @author rjaywant
 * 
 * @param <T>
 */
public class DefaultBoService<T extends Bo> implements BoService<T> {

    private final Dao<T> dao;

    public DefaultBoService(Dao<T> dao) {
        super();
        this.dao = dao;
    }

    @Override
    public T load(String id) {
        return dao.load(id);
    }

    @Override
    public void add(T item) {
        dao.add(item);
    }

    @Override
    public void delete(T item) {
        dao.delete(item);
    }

    @Override
    public void update(T item) {
        dao.update(item);
    }

    @Override
    public List<T> getList(Criteria<T> criteria) {
        return dao.filter(criteria, new ArrayList<T>());
    }

    @Override
    public Set<T> getSet(Criteria<T> criteria) {
        return dao.filter(criteria, new HashSet<T>());
    }
}
