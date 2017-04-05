package insite.shiftmanager.api;

import java.util.Date;
import java.util.List;

/**
 * Provides parameters for displaying the Calendar.
 * 
 * @author ashaikh
 * 
 */
public class SmCalendar {

    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private List<Date> selectableDates;

    public List<Date> getSelectableDates() {
        return selectableDates;
    }

    public void setSelectableDates(List<Date> selectableDates) {
        this.selectableDates = selectableDates;
    }

    private List<SmShiftDate> shiftDates;

    public List<SmShiftDate> getShiftDates() {
        return shiftDates;
    }

    public void setShiftDates(List<SmShiftDate> selectedDates) {
        this.shiftDates = selectedDates;
    }

}
