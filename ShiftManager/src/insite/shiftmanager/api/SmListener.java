package insite.shiftmanager.api;

import java.util.Date;
import java.util.List;

/**
 * Listener for Shift Manager Widget events.
 * 
 * @author ashaikh
 * 
 * @param <BO>
 *            represents a business object such as JobPosting for which the
 *            SmListener is being implemented.
 */
public interface SmListener<BO> {

    /**
     * Initial entry point.
     * 
     * @param context
     * @return List of Shifts
     */
    List<SmShift> onLoad(SmContext<BO> context);

    /**
     * Refresh Calendar handler
     * 
     * @param context
     * @param shiftIds
     * @return Calendar with selected shiftDates
     */
    SmCalendar onRefreshCalendar(SmContext<BO> context, List<String> shiftIds);

    /**
     * Validate the given shiftDate.
     * 
     * @param context
     * @param shiftDate
     * @return validation messages
     */
    SmMessages onValidateShiftDate(SmContext<BO> context, SmShiftDate shiftDate);

    /**
     * Provide a shift-wise summary for the given dates.
     * 
     * @param context
     * @param shiftIds
     * @param date
     * @return shiftDateInfo
     */
    List<SmShiftDateInfo> onShiftDateInfo(SmContext<BO> context, List<String> shiftIds, Date date);

    /**
     * Update the shiftDates.
     * 
     * @param context
     *            can be used to determined whether to do in-memory updates or
     *            persist to db
     * @param shiftDates
     *            the dates to add/delete/update.
     * @return validation/update errors/messages.
     */
    SmMessages onUpdateShiftDates(SmContext<BO> context, List<String> shiftIds, List<SmShiftDate> shiftDates);
}
