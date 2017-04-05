package core.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import core.bean.Beans;
import core.bean.Criteria;
import core.bo.Bo;
import core.dao.DefaultDao;

/**
 * 
 * @author rjaywant
 * 
 */
public class BoUtils {

    public static <T extends Bo> BoService<T> getService(Class<T> boClass) {
        @SuppressWarnings("unchecked")
        BoService<T> boService = Beans.get(BoService.class, boClass.getSimpleName() + "Service");
        return boService;
    }

    public static <T extends Bo> void addService(Class<T> boClass) {
        DefaultDao<T> dao = new DefaultDao<T>(boClass);
        Beans.register(dao, boClass.getSimpleName() + "Dao");
        Beans.register(new DefaultBoService<T>(dao), boClass.getSimpleName() + "Service");
    }

    public static <T extends Bo> T getBo(Class<T> boClass, String id) {
        return getService(boClass).load(id);
    }

    public static <T extends Bo> Set<T> getBoSet(Class<T> boClass, String propName, Object value) {
        Criteria<T> c = Criteria.create(boClass, propName, value);
        return getService(boClass).getSet(c);
    }

    public static <T extends Bo> List<T> getBoList(Class<T> boClass, String propName, Object value) {
        Criteria<T> c = Criteria.create(boClass, propName, value);
        return getService(boClass).getList(c);
    }

    public static <T extends Bo> List<T> getBoList(Criteria<T> c) {
        return getService(c.getBeanClass()).getList(c);
    }

    public static <T extends Bo> String getId(T bo) {
        if (bo == null) {
            return null;
        }
        return bo.getId();
    }

    public static <T extends Bo> void insert(T bo) {
        @SuppressWarnings("unchecked")
        Class<T> boClass = (Class<T>) bo.getClass();
        getService(boClass).add(bo);
    }

    public static <T extends Bo> void deleteAll(Collection<T> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        T bo = items.iterator().next();
        @SuppressWarnings("unchecked")
        Class<T> boClass = (Class<T>) bo.getClass();
        BoService<T> service = getService(boClass);
        for (T item : items) {
            service.delete(item);
        }
    }

    public static <T extends Bo> void addAll(Collection<T> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        T bo = items.iterator().next();
        @SuppressWarnings("unchecked")
        Class<T> boClass = (Class<T>) bo.getClass();
        BoService<T> service = getService(boClass);
        for (T item : items) {
            service.add(item);
        }
    }

    public static <T extends Bo> void updateAll(Collection<T> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        T bo = items.iterator().next();
        @SuppressWarnings("unchecked")
        Class<T> boClass = (Class<T>) bo.getClass();
        BoService<T> service = getService(boClass);
        for (T item : items) {
            service.update(item);
        }
    }

}
