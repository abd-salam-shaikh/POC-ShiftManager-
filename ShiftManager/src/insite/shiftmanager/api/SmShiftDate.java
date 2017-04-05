package insite.shiftmanager.api;

import java.util.Date;

/**
 * Represents shift and date selections. Also keeps track of the Add/Delete/Edit
 * operation.
 * 
 * @author ashaikh
 * 
 */
public class SmShiftDate {

    private String shiftId;

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private SmAction action = SmAction.NONE;

    public SmAction getAction() {
        return action;
    }

    public void setAction(SmAction action) {
        this.action = action;
    }

}
