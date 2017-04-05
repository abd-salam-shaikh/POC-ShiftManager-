package insite.shiftmanager.api;

/**
 * Summarized information for a shift date.
 * 
 * @author ashaikh
 * 
 */
public class SmShiftDateInfo {

    private String shiftId;

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    private int requested;

    public int getRequested() {
        return requested;
    }

    public void setRequested(int requested) {
        this.requested = requested;
    }

    private int assigned;

    public int getAssigned() {
        return assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }

}
