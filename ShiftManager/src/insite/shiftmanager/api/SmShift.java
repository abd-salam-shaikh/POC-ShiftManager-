package insite.shiftmanager.api;

/**
 * 
 * Represents a Shift
 * 
 * @author ashaikh
 * 
 */
public class SmShift {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return (id + name).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (SmShift.class.isInstance(obj) == false) {
            return false;
        }
        SmShift other = (SmShift) obj;
        return this.id.equals(other.id) && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return String.format("id: %s, name: %s", id, name);
    }

}
