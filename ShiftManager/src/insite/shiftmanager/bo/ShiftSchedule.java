package insite.shiftmanager.bo;

import core.bo.Bo;

/**
 * ShiftSchedule admin object.
 * 
 * @author ashaikh
 * 
 */
public class ShiftSchedule extends Bo {

    private String shortCode;

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
