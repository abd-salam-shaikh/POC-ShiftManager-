package core.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author rjaywant
 * 
 * @param <T>
 */
public class Criteria<T> {

    private final Class<T> beanClass;

    private List<Condition> conditions = new ArrayList<Condition>();

    private Criteria(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<T> getBeanClass() {
        return this.beanClass;
    }

    public void addWhereEquals(//
            String property, Object value) {
        conditions.add(new Condition(property, Operation.EQUALS, value));
    }

    public void addWhereEquals(//
            String property1, Object value1,//
            String property2, Object value2) {
        conditions.add(new Condition(property1, Operation.EQUALS, value1));
        conditions.add(new Condition(property2, Operation.EQUALS, value2));
    }

    public void addWhereEquals(//
            String property1, Object value1,//
            String property2, Object value2,//
            String property3, Object value3) {
        conditions.add(new Condition(property1, Operation.EQUALS, value1));
        conditions.add(new Condition(property2, Operation.EQUALS, value2));
        conditions.add(new Condition(property3, Operation.EQUALS, value3));
    }

    public void addWhereEquals(Map<String, Object> map) {
        for (Entry<String, Object> entry : map.entrySet()) {
            conditions.add(new Condition(entry.getKey(), Operation.EQUALS, entry.getValue()));
        }
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }

    public static <T> Criteria<T> create(Class<T> beanClass) {
        return new Criteria<T>(beanClass);
    }

    public static <T> Criteria<T> create(//
            Class<T> beanClass, //
            String property, Object value) {
        Criteria<T> c = new Criteria<T>(beanClass);
        c.addWhereEquals(property, value);
        return c;
    }

    public static <T> Criteria<T> create(//
            Class<T> beanClass, //
            String property1, Object value1,//
            String property2, Object value2) {
        Criteria<T> c = new Criteria<T>(beanClass);
        c.addWhereEquals(property1, value1);
        c.addWhereEquals(property2, value2);
        return c;
    }

    public static <T> Criteria<T> create(//
            Class<T> beanClass, //
            String property1, Object value1,//
            String property2, Object value2,//
            String property3, Object value3) {
        Criteria<T> c = new Criteria<T>(beanClass);
        c.addWhereEquals(property1, value1);
        c.addWhereEquals(property2, value2);
        c.addWhereEquals(property3, value3);
        return c;
    }

}
