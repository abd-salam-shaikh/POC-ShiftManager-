package insite.shiftmanager.ui;

import insite.shiftmanager.api.*;
import insite.shiftmanager.bo.JobPosting;
import insite.shiftmanager.bo.JobPostingShift;
import insite.shiftmanager.bo.ShiftSchedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import core.service.BoUtils;

public class JobPostingSmListener implements SmListener<JobPosting> {

    @Override
    public List<SmShift> onLoad(SmContext<JobPosting> context) {
        String id = context.getObjectId();
        JobPosting jp = context.getObject();
        if (jp == null) {
            jp = BoUtils.getBo(JobPosting.class, id);
        }
        List<SmShift> shifts = new ArrayList<SmShift>();
//        for (ShiftSchedule ss : jp.getShiftSchedules()) {
//            SmShift shift = new SmShift();
//            shift.setId(ss.getId());
//            shift.setName(ss.getName());
//            shifts.add(shift);
//        }
        return shifts;
    }

    @Override
    public SmCalendar onRefreshCalendar(SmContext<JobPosting> context, List<String> shiftIds) {
        JobPosting jp = context.getObject();
        SmCalendar smCalendar = new SmCalendar();
        smCalendar.setStartDate(jp.getStartDate());
        smCalendar.setEndDate(jp.getEndDate());
        smCalendar.setSelectableDates(new ArrayList<Date>());
        List<SmShiftDate> selectedDates = new ArrayList<SmShiftDate>();
        
        smCalendar.setShiftDates(selectedDates);
        return null;
    }

    @Override
    public SmMessages onValidateShiftDate(SmContext<JobPosting> context, SmShiftDate shiftDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SmShiftDateInfo> onShiftDateInfo(SmContext<JobPosting> context, List<String> shiftIds, Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SmMessages onUpdateShiftDates(SmContext<JobPosting> context, List<String> shiftIds, List<SmShiftDate> shiftDates) {
        // TODO Auto-generated method stub
        return null;
    }
}
