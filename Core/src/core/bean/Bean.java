package core.bean;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author rjaywant
 * 
 * @param <T>
 */
public class Bean<T> {

    private final Class<T> beanClass;

    private final Map<String, PropInfo> properties = new HashMap<String, PropInfo>();
    private final Set<Class<?>> skipCopyProperties = new HashSet<Class<?>>();

    /**
     * Skips these properties during clone/copy operations.
     * 
     * @param classes
     */
    public void addSkipCopyProperties(Class<?>... classes) {
        for (Class<?> c : classes) {
            skipCopyProperties.add(c);
        }
    }

    public Bean(Class<T> beanClass) {
        this.beanClass = beanClass;
        loadProps(beanClass, properties);
    }

    private static void loadProps(Class<?> beanClass, Map<String, PropInfo> properties) {
        Map<String, Method> getters = new HashMap<String, Method>();
        Map<String, Method> setters = new HashMap<String, Method>();
        for (Method m : beanClass.getMethods()) {
            if (m.getParameterTypes().length == 1 && m.getName().startsWith("set")) {
                String propName = Character.toLowerCase(m.getName().charAt(3)) + m.getName().substring(4);
                setters.put(propName, m);
            } else if (m.getParameterTypes().length == 0 && m.getName().startsWith("get")) {
                String propName = Character.toLowerCase(m.getName().charAt(3)) + m.getName().substring(4);
                getters.put(propName, m);
            } else if (m.getParameterTypes().length == 0 && m.getName().startsWith("is") && isBoolean(m.getReturnType())) {
                String propName = Character.toLowerCase(m.getName().charAt(2)) + m.getName().substring(3);
                getters.put(propName, m);
            }
        }
        for (Entry<String, Method> entry : setters.entrySet()) {
            String propName = entry.getKey();
            Method setter = entry.getValue();
            Method getter = getters.get(propName);
            if (getter == null) {
                // must have both getter and setter.
                continue;
            }
            properties.put(propName, new PropInfo(propName, getter, setter));
        }
    }

    private static boolean isSkipClass(Set<Class<?>> skipClasses, Method getter) {
        boolean isSkipClass = false;
        for (Class<?> c : skipClasses) {
            if (c.isAssignableFrom(getter.getReturnType())) {
                isSkipClass = true;
                break;
            }
        }
        return isSkipClass;
    }

    private static boolean isBoolean(Class<?> returnType) {
        return boolean.class.isAssignableFrom(returnType) || Boolean.class.isAssignableFrom(returnType);
    }

    private static class PropInfo {
        private final String name;
        private final Method getter;
        private final Method setter;

        public PropInfo(String name, Method getter, Method setter) {
            this.name = name;
            this.getter = getter;
            this.setter = setter;
        }
    }

    public T clone(T src) {
        T dest = null;
        try {
            dest = beanClass.newInstance();
            copyValues(src, dest);
        } catch (Exception e) {
            throw new RuntimeException(beanClass.getName(), e);
        }
        return dest;
    }

    public <C extends Collection<T>> C cloneValues(Collection<T> values, C result) {
        for (T value : values) {
            result.add(clone(value));
        }
        return result;
    }

    public void copyValues(T src, T dest, String... props) {
        boolean allProps = props == null || props.length == 0;
        Collection<String> names = null;
        if (allProps) {
            names = properties.keySet();
        } else {
            names = Arrays.asList(props);
        }
        for (String name : names) {
            PropInfo pi = getPropInfo(name);
            if (isSkipClass(skipCopyProperties, pi.getter)) {
                continue;
            }
            Object value = getValue(src, pi);
            setValue(dest, pi, value);
        }
    }

    public void setValue(T object, String propName, Object value) {
        PropInfo pi = getPropInfo(propName);
        setValue(object, pi, value);
    }

    private void setValue(T dest, PropInfo pi, Object value) {
        try {
            pi.setter.invoke(dest, new Object[] { value });
        } catch (Exception e) {
            throw new RuntimeException(pi.setter.getName(), e);
        }
    }

    public Object getValue(T object, String propName) {
        PropInfo pi = getPropInfo(propName);
        return getValue(object, pi);
    }

    private PropInfo getPropInfo(String propName) {
        PropInfo pi = properties.get(propName);
        if (pi == null) {
            throw new RuntimeException("invalid property " + propName);
        }
        return pi;
    }

    private Object getValue(T src, PropInfo pi) {
        Object value = null;
        try {
            value = pi.getter.invoke(src, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(pi.getter.getName(), e);
        }
        return value;
    }

    public Map<String, Object> getValues(T o) {
        Map<String, Object> values = new HashMap<String, Object>();
        for (PropInfo pi : properties.values()) {
            try {
                Object value = pi.getter.invoke(o, new Object[0]);
                values.put(pi.name, value);
            } catch (Exception e) {
                throw new RuntimeException(pi.getter.getName(), e);
            }
        }
        return values;
    }

    public void setValues(T o, Map<String, Object> values) {
        for (Entry<String, Object> entry : values.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            PropInfo pi = getPropInfo(name);
            setValue(o, pi, value);
        }
    }

}
