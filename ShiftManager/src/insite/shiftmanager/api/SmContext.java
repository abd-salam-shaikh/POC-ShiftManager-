package insite.shiftmanager.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the environment for the SmListener. Can be thought of as a
 * mini-session. Note that it does not assume presence of any HTTP Servlet
 * objects such as request or session. This means that this framework can also
 * be used in non-web scenarios (integration?).
 * 
 * @author ashaikh
 * 
 * @param <BO>
 *            represents the business object such as JobPosting
 */
public class SmContext<BO> {

    private String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    private BO object;

    public BO getObject() {
        return object;
    }

    public void setObject(BO object) {
        this.object = object;
    }

    private Map<String, Object> attributes = new HashMap<String, Object>();

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

}
