package insite.shiftmanager.api;

/**
 * Represents a single message
 * 
 * @author ashaikh
 * 
 */
public class SmMessage {

    private SmMessageType type;

    public SmMessageType getType() {
        return type;
    }

    public void setType(SmMessageType type) {
        this.type = type;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SmMessage(SmMessageType type, String message) {
        this.type = type;
        this.message = message;
    }

}
