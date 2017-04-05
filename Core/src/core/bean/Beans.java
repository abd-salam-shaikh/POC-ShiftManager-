package core.bean;

import java.util.*;

/**
 * 
 * @author rjaywant
 * 
 */
public class Beans {

    private static Map<String, Object> registry = new HashMap<String, Object>();

    public static List<String> getNames() {
        return new ArrayList<String>(registry.keySet());
    }

    public static <I, S extends I> void register(S object, Class<I> interfaceClass) {
        register(object, interfaceClass.getName());
    }

    public static void register(Object object, String name) {
        if (Initializable.class.isInstance(object)) {
            ((Initializable) object).initialize();
        }
        registry.put(name, object);
    }

    public static <T> T get(Class<T> interfaceClass) {
        return get(interfaceClass, interfaceClass.getName());
    }

    public static <T> T get(Class<T> interfaceClass, String name) {
        @SuppressWarnings("unchecked")
        T object = (T) registry.get(name);
        return object;
    }

    public static void dispose() {
        for (Object o : registry.values()) {
            if (Disposable.class.isInstance(o)) {
                try {
                    ((Disposable) o).dispose();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

}
