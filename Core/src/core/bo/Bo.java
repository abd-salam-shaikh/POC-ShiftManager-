package core.bo;

import java.util.concurrent.atomic.AtomicInteger;

import core.bean.Identifiable;

/**
 * 
 * @author rjaywant
 * 
 */
public abstract class Bo implements Identifiable {

    private static AtomicInteger nextId = new AtomicInteger(1);

    private String id;

    public String getId() {
        if (id == null) {
            id = getClass().getSimpleName() + nextId.incrementAndGet();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass().isInstance(obj) == false) {
            return false;
        }
        return this.id.equals(((Bo) obj).id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return this.id;
    }

}
