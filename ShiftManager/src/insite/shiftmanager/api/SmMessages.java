package insite.shiftmanager.api;

import java.util.*;

/**
 * Represents a collection of messages for different fields.
 * 
 * @author ashaikh
 * 
 */
public class SmMessages {

    private Map<String, List<SmMessage>> messages = new HashMap<String, List<SmMessage>>();

    public void addMessage(String field, SmMessageType type, String text) {
        List<SmMessage> list = messages.get(field);
        if (list == null) {
            list = new ArrayList<SmMessage>();
            messages.put(field, list);
        }
        list.add(new SmMessage(type, text));
    }

    public Map<String, List<SmMessage>> getMessages() {
        return messages;
    }

}
