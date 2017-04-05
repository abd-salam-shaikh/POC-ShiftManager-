package core.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import core.bean.Bean;
import core.bean.Condition;
import core.bean.Criteria;
import core.bean.Disposable;
import core.bean.Identifiable;
import core.bean.Initializable;
import core.bo.Bo;

/**
 * In-Memory dao implementation.
 * 
 * @author rjaywant
 * 
 * @param <T>
 */
public class DefaultDao<T extends Identifiable> implements Dao<T>, Initializable, Disposable {

    private final Map<String, T> items = new LinkedHashMap<String, T>();

    private final Bean<T> bean;

    public DefaultDao(Class<T> itemClass) {
        this.bean = new Bean<T>(itemClass);
        this.bean.addSkipCopyProperties(Collection.class, Bo.class);
    }

    @Override
    public T load(String id) {
        if (items.containsKey(id) == false) {
            throw new NotFoundException(id);
        }
        return items.get(id);
    }

    @Override
    public void add(T item) {
        if (items.containsKey(item.getId())) {
            throw new DuplicateException(item.getId());
        }
        items.put(item.getId(), bean.clone(item));
    }

    @Override
    public void delete(T item) {
        if (items.containsKey(item.getId()) == false) {
            throw new NotFoundException(item.getId());
        }
        items.remove(item.getId());
    }

    @Override
    public void update(T item) {
        if (items.containsKey(item.getId()) == false) {
            throw new NotFoundException(item.getId());
        }
        items.put(item.getId(), bean.clone(item));
    }

    @Override
    public void initialize() {
        // TODO: load from disk
    }

    @Override
    public void dispose() throws Exception {
        // TODO: persist to disk
    }

    public <C extends Collection<T>> C filter(Criteria<T> criteria, C result) {
        result.clear();
        for (T item : items.values()) {
            if (criteria == null || match(criteria, item) == true) {
                result.add(bean.clone(item));
            }
        }
        return result;
    }

    private boolean match(Criteria<T> criteria, T item) {
        for (Condition c : criteria.getConditions()) {
            if (isEqual(bean.getValue(item, c.getProperty()), c.getValue()) == false) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEqual(Object v1, Object v2) {
        if (v1 == v2) {
            return true;
        }
        if (v1 == null) {
            return false;
        }
        return v1.equals(v2);
    }

}
